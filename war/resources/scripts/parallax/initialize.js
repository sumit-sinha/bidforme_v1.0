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