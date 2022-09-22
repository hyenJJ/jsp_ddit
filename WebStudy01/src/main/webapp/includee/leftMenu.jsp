<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <nav id="sidebarMenu" class="col-md-2 col-lg-2 d-md-block bg-light sidebar collapse">
      <div class="sidebar-sticky pt-3">
        <ul class="nav flex-column">      
			<li class="nav-item">                                            
				<a class="nav-link active" href="<%=request.getContextPath() %>?command=CALENDAR">Calendar</a>
			</li>
			<li class="nav-item">
				<a class="nav-link active" href="<%=request.getContextPath() %>?command=FACTORIAL">Factorial</a>
			</li>	
			<li class="nav-item">
				<a class="nav-link active" href="<%=request.getContextPath() %>?command=CALCULATOR">Calculator</a>
			</li>	
        </ul>
      </div>
    </nav>