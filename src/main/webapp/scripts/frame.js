document.observe('dom:loaded', function() {
	RowSelector.bindEvents();
});


function validateForm()
{
var x=document.forms["addFrame"]["price"].value;
var numbers = /^[0-9.]+$/;
if (!x.match(numbers))
  {
  alert("Price should be a float only");
  return false;
  }
}