﻿var info;
var title;
var current;
var JSONP_Response;

$(document).ready(function () {

    $("#search_title_button").click(function () {

        document.getElementById('searchResults').innerHTML = "";

        title = $("input#title").val().trim();
        //current = $("username").val().trim();

        CallAPI(title);
    });

    $('input#title').keydown(function (e) {
        var key;
        if (e.charCode) {
            key = e.charCode;
        }
        else if (e.keyCode) {
            key = e.keyCode;
        }
        else {
            key = 0;
        }
        if (key == 13 || key==10) {
            e.preventDefault();
            document.getElementById('searchResults').innerHTML = "";

            title = $("input#title").val().trim();
            CallAPI(title);
        }
    });
});

function CallAPI(title) {
	
   
            $.ajax({
                url: "https://www.googleapis.com/books/v1/volumes?q="+title+"&maxResults=30",
                dataType: "jsonp",
                success: function (response) {
                    format_data(response);
            },
            error: function (xhr, status, err) {
                document.getElementById('searchResults').innerHTML = '<br/><center><h2>The API is not responding, please try again.</h2></center>';
            }
        });
}

function format_data(JSONP_Response) {

    var items = JSONP_Response.items;
    var current = jQuery("#username").val();
   // var current_book_id = jQuery("#username").val();

    if (typeof items != 'undefined') {

        var number_of_items = JSONP_Response.items.length;

        var id;

        var theader = "<div class='row'>";
        var tbody = "";


        for (var i = 0; i < number_of_items; i++) {

            id = JSONP_Response.items[i].id;

            var obj1 = JSONP_Response.items[i].volumeInfo.imageLinks;

            var previewLink = "viewbook.jsp?username="+current+"&id=" + id;
            if (typeof obj1 != 'undefined') {
                var thumbNail = JSONP_Response.items[i].volumeInfo.imageLinks.thumbnail;
                var thumbNailProcessed = "<img class='img-rounder' src=" + thumbNail + " id='bookImages'>";
                tbody += "<a href=" + previewLink + " target='_blank'>";
                tbody += thumbNailProcessed;
                tbody += "</a>";
                
            }
            else {
                var no_image = "<img src='../images/no_thumbnail.jpg' id='noImage1'/>";
                tbody += "<a href="+previewLink+" target='_blank'>" + no_image + "</a>";
            }          
        }
  

        document.getElementById('searchResults').innerHTML = theader + tbody + "</div>";
    }
    if (typeof items == 'undefined') {
        document.getElementById('searchResults').innerHTML = "<br/><center><h2 id='nobookHead'>No books found matching your entry. Please try a different name.</h2></center><br/>";
    }
}
