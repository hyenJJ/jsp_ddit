package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractCommandHandler {
	public abstract String process(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException;
}
