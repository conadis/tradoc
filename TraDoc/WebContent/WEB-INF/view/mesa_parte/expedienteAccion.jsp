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
            
				
				gridAccion = $("#listAccion");
				var uoSel = -1;
				var acSel = -1;
			    $(document).ready(function() {
			    
			    	
					window.prettyPrint() && prettyPrint();
					var dataJs = '${dataJsonAccion}';
					uoSel = '${uoSeleccionada}';
					acSel = '${accionSeleccionadas}';
					llenarGridAccion(eval(dataJs));
										
					var seleAcci = []; // Create a new empty array.
					seleAcci = acSel.split(",");
					//alert(seleAcci);

					for (var i = 0; i < seleAcci.length; i++) {
						if(!isNaN(seleAcci[i])){
							jQuery("#listAccion").jqGrid('setSelection',seleAcci[i]);
						}
						
					}
					
					
                    
			    });

				$(function() {
					  $('#boxcloseAccion').click(function(){
	                        $('#boxAccion').animate({'top':'30%'},500,function(){
	                            $('#overlayAccion').fadeOut('fast');
	                        });
	                    });
	                    
	                    $('#cancelAccion').click(function(){
	                        $('#boxAccion').animate({'top':'30%'},500,function(){
	                            $('#overlayAccion').fadeOut('fast');
	                        });
	                    });
					
                    
	                   
	                    $('#guardarAccion').click(function(){
	                    		//Elementos seleccionados
	                    		var selacc = jQuery("#listAccion").jqGrid('getGridParam','selarrrow');
	                    		//jQuery("#listAccion").jqGrid('setSelection',"1");
	                    		//var datsel = jQuery("#listAccion").jqGrid('getRowData',1);
	                    		
	                    		//var selectedRowId = $('#list').jqGrid ('getGridParam', 'selrow');
	                    		//var columnName = $('#list').jqGrid('getCell', selectedRowId, 'columnName');
	                    		
	                    		//alert("cod="+datsel.codacc+" des="+datsel.desacc);
						 		$.ajax({
						 					data: "selAcciones=" + selacc+"&uoSeleccionada="+uoSel,
						 					url: "../addAccion.htm",
						 					type: "POST", 
						 					success:function(data){
						 					if(data =='error'){
						 						alert("Ocurrio un error al guardar la(s) acción(s), consulte con el adminitrador");
						 					}else{
													$('#boxAccion').animate({'top':'100px'},500,function(){
					                            		$('#overlayAccion').fadeOut('fast');
				                        			});
			                    				}
			                    			}
			                    
			                    });
								
							 
	                    });



                });
				
				function llenarGridAccion(Json) {
					gridAccion.jqGrid({
						datatype : 'local',
						data : Json,
						colNames : [ "idAccion", "Acción"],
						colModel : [ {
							name : 'codacc',
							index : 'name',
							align : "center",
							viewable : false,
							hidden : true,
							sortable : false
						}, {
							name : 'desacc',
							index : 'name',
							width : 350,
							align : "left",
							sortable : false
						},  ],
						
						

						rowNum : 10,
						rowList : [ 5, 10, 20 ],

						pager : '#pagerAccion',
						//pginput : false,

						gridview : true,
						rownumbers : false,
						sortname : 'coduo',
						viewrecords : false,
						multiselect:true,
						multiPageSelection:true,
						sortorder : 'asc',

						width : 450,
						height : '100%'

						

					});

				}
                
			
			</script>
</head>
<body>

				
                <div  class="overlay" id="overlayAccion" style="display:none;">
                	<div class="box" id="boxAccion" style="width:455px;top:20px;left:250px">
                    <a class="boxclose" id="boxcloseAccion" onclick="cerrar();"></a>
					
					      
					
                        <form:form  id="frmAccion" enctype="multipart/form-data" method="post" class="scrollbar" >
                            <ul id="body-popup">
							           <div id="container-grid">
											<div class="table-grid" style="margin: 0 0 0 0;">
												<div class="tr-grid">
													<div class="td-grid" ></div>
												</div>
											</div>
											<table id="listAccion"></table>
									   		<div id="pagerAccion"></div>
										</div>
                            </ul>
                            
							
                            <ul id="button-popup">
                            	<li><input type="button" id="guardarAccion"  value="Aceptar" class="button-image-complete-gren" ></li>
                            	<li><input type="button" id="cancelAccion" onclick="cerrar();" value="Cancelar" class="button-image-complete"><li>
                            </ul>
                        </form:form>


                    </div>
                </div>

</body>
</html>