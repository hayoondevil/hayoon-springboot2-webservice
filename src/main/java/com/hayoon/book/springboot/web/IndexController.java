package com.hayoon.book.springboot.web;

import com.hayoon.book.springboot.config.auth.LoginUser;
import com.hayoon.book.springboot.config.auth.dto.SessionUser;
import com.hayoon.book.springboot.service.PostsService;
import com.hayoon.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        if (user != null) {
            model.addAttribute("userName", user.getName());
        } else {
            // user가 null일 경우 빈 문자열 또는 null을 넣어 환경 변수 대체를 방지
            model.addAttribute("userName", null);
            // 또는 model.addAttribute("userName", "");
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
