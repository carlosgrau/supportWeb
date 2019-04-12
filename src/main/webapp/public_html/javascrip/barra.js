var lastScrollTop = 0;
var lineheight = 50;
$(window).scroll(function(event){
   var st = $(this).scrollTop();
  
    var lineTop = $('h1 span').scrollTop();
  
   if (st > lastScrollTop){
       // downscroll code
     $('.backShapes span i').css({transform: "rotate(" + st/5 + "deg)" });
     
     if (st > lineTop && lineheight< 550){
      lineheight = lineheight + 10;
      $('h1 span').css('height',lineheight+'px');
     }
         
   } else {
      // upscroll code
     $('.backShapes span i').css({transform: "rotate(" + st/5 + "deg)" });

     if (st > lineTop && lineheight>50){
       lineheight = lineheight - 10;
      $('h1 span').css('height',lineheight+'px');
     }
   }
   lastScrollTop = st;
  
  checkAnimateIn(st);
});

function checkAnimateIn(st){
  var pageHeight = $(window).height();
  var triggerPoint = pageHeight/2 + st;
  $('.animateIn').each(function(){
    var aniTop = $(this).offset().top;
    console.log(aniTop, triggerPoint);
    if(aniTop < triggerPoint){
      $(this).addClass('open');
    }else{
      $(this).removeClass('open');
    }
  });
}

$('.navToggle').click(function(){
  $(this).toggleClass('active');
  $('.navLinks').toggleClass('active');
});