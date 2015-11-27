<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=8" />
<link rel="stylesheet" href="../css/validationEngine.jquery.css" type="text/css">

<!--[if lt IE 9]>
<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->


                      
            <script type="text/javascript">
            
				var k;
				grid3 = $("#list3");
			    $(document).ready(function() {
			    
			    	jQuery("#frmAgregarExpediente").validationEngine({});
			    	
					window.prettyPrint() && prettyPrint();
					
					$('#plaza').multiselect({
						    includeSelectAllOption: true,
							enableCaseInsensitiveFiltering: true, 
							buttonClass: 'validate[required] multiselect dropdown-toggle btn btn-default'
						});
						
					$('.btn-group button').val($('#plaza').val());

					
					//llenarGridDocumentoInternoExpediente(hh);
					
                    if("${oficina.idoficina}"== ""){
                    
                    	
                    	
                    	jQuery("#buttonFile").click(function() {
					         jQuery("#imageInput").trigger('click');
					     });

						

                    }else
	                    {	
	                    	//$("#url").attr('disabled', 'disabled');
	                    	$("#body-popup").append('<li class="no-margin">Fecha de Actualización:</li> <li id="right-li" class="no-margin"><fmt:formatDate value="${oficina.fechaactualizacion}" type="date" pattern="dd/MM/yyyy"/></li>');
	
	                    }
                    
                    
                   /* $("#form").submit(function() {  
        				$.post($(this).attr("action"), $(this).serialize(), function(html) {
        					$("#formsContent").replaceWith(html);
        					$('html, body').animate({ scrollTop: $("#message").offset().top }, 500);
        				});
        				return false;  
        				});			
        			});*/
                    
                    
			    });

				$(function() {
					  $('#boxclose').click(function(){
	                        $('#box').animate({'top':'30%'},500,function(){
	                            $('#overlay').fadeOut('fast');
	                        });
	                    });
	                    
	                    $('#cancel').click(function(){
	                        $('#box').animate({'top':'30%'},500,function(){
	                            $('#overlay').fadeOut('fast');
	                        });
	                    });
					
                    
	                   
	                    $('#guardar').click(function(){
	                    	alert("entrando...");
	                    	jQuery("#frmAgregarExpediente").validationEngine('validate');
	                    	var b = true;
	                    	var v = true;
											
							 if(b && v){
							 	alert("Entroo...");
						 		$('#frmAgregarExpediente').ajaxForm({url:"../addExpediente.htm",type:"post", success:function(data){
						 					alert("termino de enviar...");	
						 					if(data =='false'){
						 						alert("Ya existe el expediente, consulte con el administrador");
						 					}else{
						 						alert("Expediente registrado satisfactoriamente");
											$('#box').animate({'top':'100px'},500,function(){
					                            $('#overlay').fadeOut('fast');
					                            	tudata = eval(data);
					                            	
					                            	var pageSize = jQuery("#list").getGridParam("rowNum");
										            var totalRecords = jQuery("#list").getGridParam('records');
										            var totalPages = Math.ceil(totalRecords/pageSize);
										            var currentPage = jQuery("#list").getGridParam('page');
										            
					                            if($("#comunicadoHidden").val() == ""){
					                            
					                            jQuery("#list").setGridParam({data : tudata,page:totalPages}).trigger("reloadGrid");
					                            
					                            }else{
					                            	jQuery("#list").setGridParam({data : tudata,page:currentPage}).trigger("reloadGrid");
					                            }
				                        	});
			                    	}
			                    	}
			                    
			                    });
								
							 }else{
							 	return false;
							 }
	                    });



                });
				
				function llenarGridListaExpediente(Json) {

					grid3.jqGrid({
								datatype : 'local',
								data : Json,
								colNames : [ "idDocInterno", "Tipo de Documento",
										"Nro. de Documento", "Fec. de Registro", "Estado",
										"Asunto" ],
								colModel : [ {
									name : 'idInDocInterno',
									index : 'name',
									sorttype : "int",
									align : "center",
									viewable : false,
									hidden : true,
									sortable : false
								}, {
									name : 'idInTipoDocumento',
									index : 'name',
									width : 100,
									align : "center",
									sortable : false
								}, {
									name : 'idInNroDocumento',
									index : 'name',
									width : 100,
									align : "center",
									sortable : false
								}, {
									name : 'idInFecRegistro',
									index : 'name',
									width : 100,
									align : "center",
									sortable : false
								}, {
									name : 'idInEstado',
									index : 'total',
									width : 72,
									align : "center",
									sorttype : "date",
									sortable : false
								}, {
									name : 'idInAsunto',
									index : 'total',
									width : 150,
									align : "center",
									sorttype : "float",
									sortable : false
								}, ],

								rowNum : 10,
								rowList : [ 5, 10, 20 ],

								pager : '#pager3',
								//pginput : false,

								gridview : true,
								rownumbers : false,
								sortname : 'idInDocInterno',
								viewrecords : false,
								sortorder : 'asc',

								width : 673,
								height : '100%',

								gridComplete : function() {
									var ids = jQuery("#list3").jqGrid('getDataIDs');
									for (var i = 0; i < ids.length; i++) {
										var cl = ids[i];
										be = "<div id=\"icon-edit\" onclick=\"myEdit("
												+ $("#" + cl + " td").first().text()
												+ "  ,"
												+ $("#" + cl + " td").first().next().text()
												+ ")\"></div>";
										se = "<div id=\"icon-eliminar\" onclick=\"myF("
												+ $("#" + cl + " td").first().next().text()
												+ "," + cl + ")\"></div>";

										jQuery("#list3").jqGrid('setRowData', ids[i], {
											editar : be
										});
										jQuery("#list3").jqGrid('setRowData', ids[i], {
											eliminar : se
										});

									}
								}

							});

				}
                
			
			</script>
</head>
<body>

				
                <div  class="overlay" id="overlay" style="display:none;">
                	<div class="box" id="box" style="width:500px;top: 18%;">
                    <a class="boxclose" id="boxclose" onclick="cerrar();"></a>
					    <div id="title-header-popup">Listar Expediente</div>
					    
					    <table id="list"></table>
						<div id="pager"></div>
						<!-- Find Grilla -->
						<div id="container-grid">
							<div class="table-grid" style="margin: 0 0 0 0;">
								<div class="tr-grid">
									<div id="ResultadoBusqueda" class="td-grid" >
										<p style="color: #f7f7f7">Resultados de la b&uacute;squeda...</p>
									</div>
									<div class="td-grid" ></div>
								</div>
							</div>
						</div>
			    

                    </div>
                </div>
                
                <table id="list3"></table>
			<div id="pager3"></div>

</body>
</html>