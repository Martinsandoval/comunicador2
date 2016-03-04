var cond=true;

$(document).ready(iniciar);
function iniciar() {
    if(cond){
    var images = $('div.img');
    images.first().addClass('selected');
    var index = 0;
    setInterval(function() {
        images.eq(index).toggleClass('selected');
        index = (index + 1) % images.length;
        images.eq(index).toggleClass('selected');
    }, 2000);
}
}
$(document).ready(function(){
   $("div.img").on({
      "mouseover":function(){
          cond=false;
         $(this).addClass("selected");
       },
      "mouseout":function(){
         cond=true;
         $(this).removeClass("selected");
       }
   });
});


$(document).ready(function(){
$("div.img").keypress(function(event){  
       var keycode = (event.keyCode ? event.keyCode : event.which);  
      if(keycode == 13){  
           alert('Se ha presionado Enter!');  
      }   
 }); 
});




//$(document).ready(function(){
//     $("div.img").click(function(){
//        var id = $("div.img").attr("id");
//       document.location.href = "interactivos/show.xhtml?id=" + id;
//});
//});












