
$(function() {
	  $("#backtoAddress").on("click",function(e) {
	    e.preventDefault(); // cancel the link itself
	    $.post(this.href,function(data) {
	      $("#payment").html(data);
	    });
	  });
	});


function validateaddressForm() {
    var shipinfo = document.forms["address"]["shippingAdd"].value;
    var billinfo = document.forms["address"]["billingAdd"].value;

    if (shipinfo == "") {
        alert("shipping Address must be filled out");
        document.address.shippingAdd.focus();
        return false;
    }else if ( billinfo=="") {
        alert("Billing Address must be filled out");
        document.address.billingAdd.focus();
        return false;
    }
}

function validatepaymentForm() {
    var cardinfo = document.forms["payment"]["creditcardnum"].value;
    var cvvinfo = document.forms["payment"]["cvv"].value;
    var expiryinfo = document.forms["payment"]["expirydate"].value;

    if (cardinfo == "") {
        alert("credit card number must be filled out");
        document.payment.creditcardnum.focus();
        return false;
    }else if ( cvvinfo=="") {
        alert("CVV must be filled out");
        document.payment.cvv.focus();
        return false;
    }else if (expiryinfo=="") {
        alert("Card expiry date must be filled out");
        document.payment.expirydate.focus();
        return false;
    }
}

function registerCheck() {
	var x = document.forms["registrationForm"]["email"].value;
	var y = document.forms["registrationForm"]["password"].value;
	
    if (x == "") {
        alert("Email must be filled out");
        return false;
    }
    
    if (y == "") {
        alert("Password must be filled out");
        return false;
    }    
    
    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    if (reg.test(document.forms["registrationForm"]["email"].value) == false) 
    {
        alert('Invalid Email Address');
        return false;
    }
    return true;
}

function loginCheck() {
	var x = document.forms["loginForm"]["email"].value;
	var y = document.forms["loginForm"]["password"].value;
	
    if (x == "") {
        alert("Email must be filled out");
        return false;
    }
    if (y == "") {
        alert("Password must be filled out");
        return false;
    } 
    
    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    if (reg.test(document.forms["loginForm"]["email"].value) == false) 
    {
        alert('Invalid Email Address');
        return false;
    }
    return true;
}
