function autocomplet() {
	var min_length = 1; // min caracters to display the autocomplete
	
	var keyword = $('#competitions').val();
	
	if (keyword.length >= min_length) {
		$('#country_list_id').show();
		$.ajax({
			url: 'GetCompetitiors',
			type: 'GET',
			data: {keyword:keyword},
			success:function(data){
				$('#competitions').show();
				$('#country_list_id').html(data);
			}
		});
	} else {
		$('#country_list_id').hide();
	}
}
 
// set_item : this function will be executed when we select an item
function set_item(item) {
	// change input value
	$('#competitions').val(item);
	// hide proposition list
	$('#country_list_id').hide();
}