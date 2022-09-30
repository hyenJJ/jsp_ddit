/**
 * 
 */
    let updateForm = $("#updateForm");
    let updateBtn = $("#updateBtn").on("click",function(event){
       let who = viewModal.data("who");
       if(!who) return false; 
       updateForm.get(0).who.value  = who;
		updateForm.submit();	  
    });
	let deleteForm = $("#deleteForm");
	let deleteBtn = $("#deleteBtn").on("click", function(event){
	   let who = viewModal.data("who");
	   if(!who) return false;  /*데이터가 없다면*/
	   if(confirm("진짜 삭제할까?")){
		   deleteForm.get(0).who.value  = who;
		   deleteForm.submit();	   
	   }
	});
	let viewModal = $("#exampleModal").on("hidden.bs.modal",function(event){
	    
		$(this).find(".modal-body").empty();/*안에있던것을 지운다. EDD 방식*/
		viewForm.get(0).reset();
		deletBtn.data("who","")
	}).on("show.bs.modal",function(event){  /*모달이 뜨는 순간*/
		let dataTr = event.relatedTarget;
		let who = $(dataTr).data('who');
		deleteBtn.data("who",who);
	/*	*/
		
		viewForm.find('[name=who]').val(who);
		viewForm.submit();
	});
	let viewForm = $('#viewForm').on("submit",function(event){
		event.preventDefault();
		let url = this.action;
		let method = this.method;
		let data = $(this).serialize(); //ajaxForm 적용
		// 전염성이 있으면 framework화
		$.ajax({
	
			url : url, //어디로
			method : method, //어떻게
			data : data, //무엇을
			dataType : "html",
			success : function(resp) { //콜백 함수=>응답데이터를 갖고놀아야함
				viewModal.find(".modal-body").html(resp);
				/* viewModal.modal('show'); */  //jquery에 있는 모든 modal은 함수
			},
			error : function(errorResp) {
	
				console.log(errorResp.status);
				viewModal.find(".modal-body").html(errorResp.responseText);
				/* viewModal.modal('show'); */
			}
	
		});
		
		
		return false;
	});