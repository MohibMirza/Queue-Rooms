(function($) {
  "use strict"; // Start of use strict
  //show join queue toast
  $("#JoinQueue").on('click', function(e){
  	e.preventDefault();
  	bs4.pop.dialog({
  		id:'JoinQueueModal',
  		title:'Join Queue Successful',
  		content: 'You have succesfully joined the queue. Please be aware when your turn comes. To cancel your position, click the "Cancel" button.',
  		width: 600,
  		height: '',
  		target: 'body',
  		closeBtn: true,
  		escape: true,
  		autoFocus: true,
  		show: true,
  		backdrop: true,
  		drag: true,
  		btns: [
  			{
  				label: 'Cancel'
  				className: 'btn-secondary',
  				onClick(cb){}
  			}
  		]
  	})
  });

})(jQuery); // End of use strict
