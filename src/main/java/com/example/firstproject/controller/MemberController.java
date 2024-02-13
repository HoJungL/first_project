package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class MemberController {
    @Autowired // 스프링부트가 미리 생성해 놓은 리파지터리 객체 주입(DI) 으로 인해 14번 줄 주석 안해도 됨. (DI= Denpendency Injection : 의존성 주입)
    MemberRepository memberRepository; // ~tory = new ArticleRepositoryImpl(){};를 만드는게 일반적

    @GetMapping("/members/new")
    public String newMemberForm() {
        return "members/new";
    }

    @GetMapping("/signup")
    public String SignUpPage () {
        return "members/new";
    }

    @PostMapping("/join")
    public String join(MemberForm memberform) { // 폼 데이터를 DTO(data transform Object)로 받기
        log.info(memberform.toString());

        //        System.out.println(memberform.toString());  // DTO에 폼 데이터가 잘 담겼는지 확인

        // 1. DTO를 엔티티로 변환
        Member member = memberform.toEntity();
        log.info(member.toString());
//        System.out.println(member.toString()); // 1. DTO가 엔티티로 잘 변환되는지 확인 출력
        // 2. repository로 entity를 DataBase에 저장
        Member saved = memberRepository.save(member);
        log.info(saved.toString());

        //        System.out.println(saved.toString()); // 2. article이 DB에 잘 저장되는지 확인 출력
        return "redirect:/members/" + saved.getId();
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model) {
        Member member = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", member);
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model) {
        Iterable<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members/index";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);
        return "members/edit";
    }

    @PostMapping("/members/update")
    public String update(MemberForm form) { // DTO 받아오고
        log.info(form.toString());

        // 1. DTO를 Entity로 변환
        Member memberEntity = form.toEntity();
        log.info(memberEntity.toString());
        // 2. 엔티티를 DB에 저장
        // 2-1. DB에서 기존 데이터 가져오기
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
        // 2-2 기존 데이터 값 갱신하기
        if (target != null) {
            memberRepository.save(memberEntity); // 엔티티를 DB에 저장(갱신하셈)
        }
        // 3. 수정 결과 페이지로 리다이렉트하기
        return "redirect:/members/" + memberEntity.getId();
    }


    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id,
                       RedirectAttributes rttr,
                       Model model) {
        log.info("삭제 요청이 들어왔습니다!!");
        // 1. 삭제 대상 가져오기
        Member target = memberRepository.findById(id).orElse(null);
        // 2. 대상 삭제
        if (target != null) {
            memberRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제되었습니다.");
        }
        // 3. 결과 페이로 리다이렉트
        return "redirect:/members";
    }
}