package com.happycar;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
	
	public static Map<String, HttpSession> sessions = new ConcurrentHashMap<String, HttpSession>();  

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		//sessions.put(se.getSession().getId(), se.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		
	}

}
