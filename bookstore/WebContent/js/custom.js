$("search_guide").keyup(function(event){
    if(event.keyCode == 13){
        $("#search_title_button").click();
    }
});