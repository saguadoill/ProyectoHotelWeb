$(function() {		
	$.fn.datepicker.language['es'] = {
		days : [ 'Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves',
				'Viernes', 'Sábado' ],
		daysShort : [ 'Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab' ],
		daysMin : [ 'Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa' ],
		months : [ 'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
				'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre',
				'Diciembre' ],
		monthsShort : [ 'Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago',
				'Sep', 'Oct', 'Nov', 'Dic' ],
		today : 'Hoy',
		clear : 'Limpiar',
//		dateFormat : 'dd-mm-yyyy',
		dateFormat : 'yyyy-mm-dd',
		timeFormat : 'hh:ii aa',
		firstDay : 1
	}

	$(function() {
		$("#fechas").datepicker({
			minDate: new Date(),
			language : 'es',
			showOn: "button",
		    buttonImage: "/images/calendar.gif",
		    buttonImageOnly: true,
		    buttonText: "Select date",
			onSelect: function (dateText, inst) {
				 var rango = dateText ;
			        var rango = rango.split(" - ");
			        var fechaInicio  = rango[0];
			        var fechaFin = rango[1];
			        
//			        fechaInicio = fechaInicio.replace('/','-');
//			        fechaFin = fechaFin.replace('/','-');
			        
			        $("#inicio").val(fechaInicio);
			        $("#fin").val(fechaFin);

			        
			        
			        if($("#fin").val() != ""){
			        	
			           var url = $(location).attr("href");  
			           var objeto = {
				                fechaInicio : $('#inicio').val(),
				                fechaFin   : $('#fin').val(),
				                idHotel    : url.substring(url.length-1, url.length)
				            };
	
				       $.ajax({
			            type: 'POST',
			            url: '/habitacion/disponibles',
			            data: JSON.stringify(objeto),
			            contentType: 'application/json; charset=utf-8',
			            success: function(data) {
			            
			                if (data != null) {
			                    $('#resultadoHa').empty()
			                    $.each(data, function(value, e) {
			                    	switch(e.clase) {
			                        case 'low':
			                            var small = $("<div><small class='text-muted'>&#9733; &#9734; &#9734;</small></div>");
			                        break;
			                        case 'medium':
			                            var small = $("<div><small class='text-muted'>&#9733; &#9733; &#9734;</small></div>");
			                        break;
			                        case 'high':
			                            var small = $("<div><small class='text-muted'>&#9733; &#9733; &#9733;</small></div>");
			                        break;
			                    }		
			                    	
			                    	
			                    	
			                        var habitacion = $("<div class='col-lg-4 col-md-6 mb-4'><div class='card'><a href='#'>"+
			                        "<img class='card-img-top' width='100%' alt='Responsive image' height='175' src='data:image/png;base64,"+e.imagen+"'></a>"+
			                        "<div class='card-body'><p class='text-muted'>Personas: "+e.personas+"</p>"+
			                        "<p class='text-muted'> Camas: "+e.camas+"</p><h5>"+e.precio+" €</h5> </h5></div>"+
			                        "<div class='card-footer'>"+small.html()+"</div></div></div>")

			                        $('#resultadoHa').append(habitacion)
			                    })
			                }
			                
			            }
			        })
			        };
            }
		});
	});
});
