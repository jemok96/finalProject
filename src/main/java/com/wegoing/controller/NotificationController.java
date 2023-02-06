package com.wegoing.controller;

import org.springframework.web.bind.annotation.RestController;

import com.wegoing.service.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NotificationController {
	private final NotificationService notificationService;

}
