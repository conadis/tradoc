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
				grid = $("#list");
				gridUO = $("#listUO");
				
			    $(document).ready(function() {
			    
			    	jQuery("#frmDocumentoExterno").validationEngine({});
					window.prettyPrint() && prettyPrint();
					
					$('#cmbEntidadExt').multiselect({
						    includeSelectAllOption: true,
							enableCaseInsensitiveFiltering: true, 
							buttonClass: 'validate[required] multiselect dropdown-toggle btn btn-default'
						});
						
					$('.btn-group button').val($('#cmbEntidadExt').val());

					var hh = [];
					var dataJs = '${dataJsonUo}';
					var codentExtval = '${codentExt}';
					var desentExtval = '${desentExt}';
					$('#txtEntidadExtHiden').val(codentExtval);
					$('#txtEntidadExt').val(desentExtval);
					llenarGridUO(eval(dataJs));
                    
                    
			    });

				$(function() {
					  $('#boxcloseExt').click(function(){
	                        $('#boxExt').animate({'top':'30%'},500,function(){
	                            $('#overlayExt').fadeOut('fast');
	                        });
	                    });
	                    
	                    $('#cancelExt').click(function(){
	                        $('#boxExt').animate({'top':'30%'},500,function(){
	                            $('#overlayExt').fadeOut('fast');
	                        });
	                    });
					
                    
	                   
	                    $('#guardarExt').click(function(){
	                    	jQuery("#frmDocumentoExterno").validationEngine('validate');
	                    	
						 		$('#frmDocumentoExterno').ajaxForm({url:"../addDocumentoExt.htm",type:"post", success:function(data){
						 					
						 					if(data =='false'){
						 						alert("Ya existe el expediente, consulte con el administrador");
						 					}else{
						 						var evaldata = eval(data);
						 						
						 						alert("Documento externo agregado satisfactoriamente");
						 		
						 						
											$('#boxExt').animate({'top':'100px'},500,function(){
					                            $('#overlayExt').fadeOut('fast');
					                            	
					                            	var pageSize = jQuery("#listDocExtBus").getGridParam("rowNum");
										            var totalRecords = jQuery("#listDocExtBus").getGridParam('records');
										            var totalPages = Math.ceil(totalRecords/pageSize);
										            var currentPage = jQuery("#listDocExtBus").getGridParam('page');
										            
					                            
					                            
					                            jQuery("#listDocExtBus").setGridParam({data : evaldata,page:totalPages}).trigger("reloadGrid");
					                            
					                            
				                        	});
			                    	}
			                    	}
			                    
			                    });
								
							 
	                    });



                });
				
				function llenarGridUO(Json) {
					gridUO.jqGrid({
						datatype : 'local',
						data : Json,
						colNames : [ "idUO", "Unidad Orgánica","Acción","Con copia" ],
						colModel : [ {
							name : 'coduo',
							index : 'coduo',
							align : "center",
							viewable : false,
							hidden : true,
							sortable : false
						}, {
							name : 'desuo',
							index : 'desuo',
							width : 350,
							align : "left",
							sortable : false
						}, {
							name : 'coduo',
							width : 70,
							align : "center",
							sortable : false,
							editable : true,
						    search: false, 
							formatter: formatoTextoNew,
							index : 'coduo'
						},{
							name: "va2", 
							 width: 70, 
							 align: "center",
					         formatter: "checkbox", 
					         formatoptions: { disabled: false},
					         edittype: "checkbox", 
					         editoptions: {value: "Yes:No", defaultValue: "Yes"},
					         stype: "select", 
					         value: ":Any;true:Yes;false:No"
						}, ],
						
						

						rowNum : 10,
						rowList : [ 5, 10, 20 ],

						pager : '#pagerUO',
						//pginput : false,

						gridview : true,
						rownumbers : false,
						sortname : 'coduo',
						viewrecords : false,
						multiselect:true,
						multiPageSelection:true,
						sortorder : 'asc',

						width : '100%',
						height : '100%'

						

					});

				}
				
				function agregarAccion(uo){
					var selRowId = gridUO.jqGrid ('getGridParam', 'selrow');
					//alert(uo);
			     	$("#accionForm").load("../upAgregarAccion.htm?uoSeleccionada="+uo, $("#accionForm").serialize(), function(){
			     		$('#overlayAccion').fadeIn('fast',function(){
			                $('#boxAccion').animate({'top':'20%'},500);
			            });
			     	});
			     	return false;
				}
				
				function formatoTextoNew(cellvalue, options, rowObject){
					//return "<a href='#' onClick='agregarAccion(\"" + rowObject._id + "\")'>" + cellvalue + "</a>"; 
					return "<a href='#' onClick='agregarAccion(\"" + cellvalue + "\")'>Add</a>";
				}
                
			
			</script>
</head>
<body>

				
                <div  class="overlay" id="overlayExt" style="display:none;">
                	<div class="box" id="boxExt" style="width:800px;top:20px;left:250px">
                    <a class="boxclose" id="boxcloseExt" onclick="cerrar();"></a>
					
					       <div id="title-header-popup">Registrar Documento Externo</div>
					
                        <form:form  id="frmDocumentoExterno" enctype="multipart/form-data" method="post" class="scrollbar" modelAttribute="documentoVO">
                            <ul id="body-popup">
                             <fieldset  class="fieldset">
    						<legend>Registro del Documento Externo:</legend>
                              <table align="center"  width="100%" border="0" cellpadding="3" cellspacing="1">
                                <tr>
                            	   <td>Tipo de Tramite:</td>
                            	   <td>
                            	     	<form:select id="cmbTipoTramite"  path="codTipoTramite" 
														class="select-box" cssStyle="height: 25px;">
															<form:option value="1" label="Transparencia" />
															<form:option value="2" label="Congreso" />
										</form:select>
                            	   </td>
                            	   <td>Fecha de Ingreso:</td>
									<td><form:input id="txtFecCreaExt" path="fechaCreacion" style="width: 80px; height: 25px;" type="text" readonly="true" /></td>
									<td>Creado Por:</td>
									<td><form:input id="txtCreaPorExt" path="codUsuCreacion" readonly="true" style="width: 80px; height: 25px;" type="text" class="input-border"/></td>
                                </tr>
                                <tr>
                            	   <td>Enviado Por:</td>
                            	   <td colspan="2">
                            	   		<form:input id="txtEntidadExt" path="desEntidadExtUO" readonly="true" style="width: 150px; height: 25px;" type="text" class="input-border"/>
                            	   		<form:hidden id="txtEntidadExtHiden" path="codEntidad" />
                            	   </td> 
                            	   <td>Nro.Documento:</td>
                            	   <td colspan="2"><form:input type="text" id="txtDocumentoExt" style="height: 25px;" class="validate[required] input-popup-3" path="desNumDocumento"  maxlength="60"/></td>
                            	                           	   
                                </tr>                                
                                <tr> 
								   <td>Descripci&oacute;n:</td>
									<td colspan="5"><form:textarea id="txtDescripcionExpAdd" path="descripcion" class="validate[required]"  style="resize: none;height: 30px;" rows="3" cols="10"></form:textarea></td>
                                </tr>           
                     			<tr>
                     			
                     				 <td>Dirigido a:</td>
                            	   <td colspan="5">
                            	   	   <form:hidden id="txtEntidadExtHiden" path="desUO" />
							           <div id="container-grid">
											<div class="table-grid" style="margin: 0 0 0 0;">
												<div class="tr-grid">
													<div class="td-grid" ></div>
												</div>
											</div>
											<table id="listUO"></table>
									   		<div id="pagerUO"></div>
										</div>
										
                            	   </td>
										
                                </tr>                                                                                                                       
                                                             
                              </table>
					</fieldset>
								
                            </ul>
                            
							
                            <ul id="button-popup">
                            	<li><input type="submit" id="guardarExt"  value="Aceptar" class="button-image-complete-gren" ></li>
                            	<li><input type="button" id="cancelExt" onclick="cerrar();" value="Cancelar" class="button-image-complete"><li>
                            </ul>
                        </form:form>


                    </div>
                </div>
                
                <div id="accionForm"></div>

</body>
</html>