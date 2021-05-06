function validatePassword(){
  	if(document.getElementById("password").value != document.getElementById("password_confirm").value) {
    	document.getElementById("message").innerHTML = "Passwords Doesn't' Match";
		document.getElementById("message").style.color = "red";
 	} else {
    	document.getElementById("message").innerHTML = "";
  	}
}

var optarray;
// to store all teams in array to fill only the teams that belongs to the selected country later
function CopyTeams() {
	optarray = $(".teamClass").first().children('option').map(function() {
        return {
            "value": this.value,
            "country":this.getAttribute('data-country-id') ,
            "option": "<option value='" + this.value + "'  data-country-id= '" + this.getAttribute('data-country-id') + "'  >" + this.text + "</option>"
        }
    })
}

// to choose the teams that belongs to the selected country
function FilterTeams(clubSelect, countryId) {
	var selectedTeam = document.getElementById(clubSelect).value;
	$("#"+clubSelect).children('option').remove();
    var addoptarr = [];
   	for (i = 0; i < optarray.length; i++) {
  		if (optarray[i].country == document.getElementById(countryId).value) {
   			addoptarr.push(optarray[i].option);
        }
    }
    $("#"+clubSelect).html(addoptarr.join(''));
    document.getElementById(clubSelect).value = selectedTeam;
}



function OnLoad(){
	CopyTeams();
	$('.countryClass').each(function() {
	  	var clubSelect = $(this).attr('data-team-id');
	    var countryId = $(this).attr('id');  
	    FilterTeams(clubSelect, countryId);
	});
}


OnLoad();

 //Call the function when we run the page (at bottom of page, or within $(document).ready or something similar)
$('.countryClass').on('change', function() { 
    var clubSelect = $(this).attr('data-team-id');
    var countryId = $(this).attr('id');
    FilterTeams(clubSelect, countryId);
});