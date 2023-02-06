package com.wegoing.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.wegoing.dao.NotificationDAO;
import com.wegoing.dto.MemberDTO;
import com.wegoing.dto.PrincipalDetails;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationInterceptor implements HandlerInterceptor{
	private final NotificationDAO notificationdao;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (modelAndView != null && !isRedirectView(modelAndView) && authentication != null 
				&& authentication.getPrincipal() instanceof PrincipalDetails) {
            MemberDTO account = ((PrincipalDetails)authentication.getPrincipal()).getMdto();
            long count = notificationdao.countByAccount(account);
            modelAndView.addObject("hasNotification", count > 0); // 알람이 있는 경우 true, 없으면 false
        }
	}
	
	private boolean isRedirectView(ModelAndView modelAndView) { // 리다이렉트 요청인지 확인하는 메서드
	      // 뷰의 이름이 redirect: 로 시작하거나, RedirectView 타입인 경우
	        return modelAndView.getViewName().startsWith("redirect:") || modelAndView.getView() instanceof RedirectView;
	    }
	
}
