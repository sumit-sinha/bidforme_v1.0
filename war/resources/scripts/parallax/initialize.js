/**
 * displays the overlay
 * @param args JSON which contains a Boolean loading, if true displays the loading icon
 */
function showOverlay(args) {
	var mskEls = document.getElementsByClassName('msk');
	for (var i = 0; i < mskEls.length; i++) {
		mskEls[i].className = 'msk';
		if (args != null && args.loading) {
			mskEls[i].className += ' loading';
		}
	}
}

/**
 * hides the overlay
 */
function hideOverlay() {
	var mskEls = document.getElementsByClassName('msk');
	for (var i = 0; i < mskEls.length; i++) {
		mskEls[i].className = 'msk loading hidden';
	}
}

/**
 * javascript function to register user
 * @returns
 */ 
function registerUser() {
	 
	 var data = "no_password_req=true";
	 var element = document.getElementById('EMAIL_1');
	 if (element != null) {
		 data += '&email=' + element.value;
	 }
	 
	 var element = document.getElementById('NAME_1');
	 if (element != null) {
		 data += '&name=' + element.value;
	 }
	 
	 var element = document.getElementById('FEEDBACK_1');
	 if (element != null) {
		 data += '&feedback=' + element.value;
	 }
	 
	 var dvError = document.getElementById('dvError');
	 if (dvError != null) {
		$("#dvError ul li").remove();
		dvError.style.display = 'none';
	 }
	 
	 showOverlay({
		 loading: true
	 });
	 
	 $.ajax({
	  url: "/register",
	  method: "POST",
	  headers: {'X-HTTP-RESULT': 'json'},
	  data: data
	}).done(function(data) {
		if (data != null 
				&& data.register != null 
				&& data.register.model != null) {
			if (data.register.model.success) {
				var pElement = document.getElementById('ulSuccess');
				if (pElement != null) {
					pElement.innerHTML = "Thank you<br/>We are delighted to recieve your feedback";
				}
			} else {
				var liContent = "<li><span>Error Messages</span></li>";
				for (var i = 0; i < data.register.error.validation_error.length; i++) {
					var error = data.register.error.validation_error[i];	
					liContent += "<li><span class='ng-binding'>" + error.message + "</span></li>"
				}
				
				$("#ulError").append(liContent);
				if (dvError != null) {
					dvError.style.display = 'block';
				}
			}
		} else {
			var liContent = "<li><span>Error Messages</span></li><li><span class='ng-binding'>Sorry!!! We encountered an unknown error. Please try again</span></li>";		
			$("#ulError").append(liContent);
			if (dvError != null) {
				dvError.style.display = 'block';
			}
		}
		
		hideOverlay();
	});
 }

$('body').scrollspy({
     target: '#my-navbar'
 })

 $(".navbar-collapse ul li a[href^='#']").on('click', function (e) {

     target = this.hash;
     // prevent default anchor click behavior
     e.preventDefault();

     // animate
     $('html, body').animate({
         scrollTop: $(this.hash).offset().top
     }, 300, function () {

         // when done, add hash to url
         // (default click behaviour)
         window.location.hash = target;
     });

 });

$(".motto a").on('click', function(e) {
    
    target = this.hash;
   // prevent default anchor click behavior
   e.preventDefault();

   // animate
   $('html, body').animate({
       scrollTop: $(this.hash).offset().top 
     }, 300, function(){

       // when done, add hash to url
       // (default click behaviour)
       window.location.hash = target;
     });

});


 $(document).ready(function () {
     // cache the window object
     $window = $(window);

     $('.full-panel').each(function () {
         // declare the variable to affect the defined data-type
         var $scroll = $(this);

         $(window).scroll(function () {
             // HTML5 proves useful for helping with creating JS functions!
             // also, negative value because we're scrolling upwards
             var yPos = -($window.scrollTop() / 6);

             // background position
             var coords = '50% ' + yPos + 'px';

             // move the background
             $scroll.css({
                 backgroundPosition: coords
             });
         }); // end window scroll
     }); // end section function
 }); // close out script