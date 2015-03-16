$(document).ready(function(){
	$(".btnTooltip").tooltip();
	
	CKEDITOR.config.enterMode = CKEDITOR.ENTER_BR;

	
	$('#nav').affix({
		offset: {
			top: 100
		}
	});

	var $submenu = $(".nav");

});
