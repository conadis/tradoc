<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=8" />

<style type="text/css">

	.error, .red{
	   color: red;
	   display:block;
	   font-family: "source-code-pro", Consolas, monospace !important;
	}
	
	.errorOFi{
	   position: absolute;
		top: 300px;
		left: 300px;
		display: block;
		cursor: pointer;
		z-index: 990;
	}
	
	#imagePreview {
	    width: 42px;
	    height:60px;
	    background: #BEE7FB;
		filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale); 
	}
	
	.blur-file {
		width: 154px;
		height: 27px;
		font-family: "Stag Sans Light", Arial, sans-serif;
		font-size: 14px;
		line-height: 27px;
		border-radius: 0 3px 3px 0;
		color: #555;
		background: #f7f7f7;
		border: 1px solid #e9e9e9;
		border-left: none;
		margin: 0;
		padding-left: 10px;
		position: absolute;
		top: 15px;
		right: -10px;
}



.legendStyle
{
    border-style:none;
    background-color: #003366;
    font-family: Tahoma, Arial, Helvetica;
    font-weight: bold;
    font-size: 9.5pt;
    Color: White;
    width:30%;
    padding-left:10px;

}



</style>

<script>
	gridDocExtBus = $("#listDocExtBus");
	grid2 = $("#list2");
	var tudata = [];

	$(document).ready(
					function() {
						
						window.prettyPrint() && prettyPrint();
						
					/*	$('#cmbRemitenteBus').multiselect({
						    includeSelectAllOption: true,
							enableCaseInsensitiveFiltering: true, 
							buttonClass: 'validate[required] multiselect dropdown-toggle btn btn-default'
						});
						$('.btn-group button').val($('#cmbRemitenteBus').val());*/
					
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
				        
						
						$("#buscarAfi").attr('disabled', 'true');
						//SE  AGREGA PARA OCULTAR LOS BOTONES AGREGAR DOC EXT
						$("#grdDocExt").hide();
						
						$("#button-image-complete-load").attr('disabled','true');

						var hh = [];
						llenarGridDocumentoExterno(hh);
						llenarGridDocumentoInterno(hh);
						
					});

	$('input[type=file]').change(function() {
		var filePath = $(this).val()//;
		$("#textisselectedanyfile").val(filePath);
		$("#button-image-complete-load").removeAttr("disabled");

	});

	

	function cerrar() {
		$("#box").animate({'top':'25%'},500,function(){
            $("#overlay").fadeOut('fast');
            $("#capituloForm").innerHtml = "";
        });
	}
	
	function linkUp() {
		$("#up").load("../upAgregarOficina.htm", function() {
			$('#overlay').fadeIn('fast', function() {
				$('#box').animate({
					'top' : '30%'
				}, 500);
				$('#title-header-popup').text("Agregar Oficina");
			});
		});
		return false;
	}

	function myEdit(t, n) {
		$("#up").load("../upEditarOficina/" + n + ".htm", function() {
			$('#overlay').fadeIn('fast', function() {
				$('#box').animate({
					'top' : '30%'
				}, 500);
				$('#title-header-popup').text("Editar Oficina");
			});
		});
		return false;
	}

	function llenarGridDocumentoExterno(Json) {
		gridDocExtBus.jqGrid({
			datatype : 'local',
			data : Json,
			colNames : [ "idDocExterno", "Editado Por", "Dirigido a",
					"Nro. de Documento", "Fec. de Registro","Estado", "Asunto" ],
			colModel : [ {
				name : 'idDocExterno',
				index : 'name',
				sorttype : "int",
				align : "center",
				viewable : false,
				hidden : true,
				sortable : false
			}, {
				name : 'envpor',
				index : 'envpor',
				width : 100,
				align : "center",
				sortable : false
			}, {
				name : 'dirgia',
				index : 'dirgia',
				width : 100,
				align : "center",
				sortable : false
			}, {
				name : 'numdoc',
				index : 'numdoc',
				width : 100,
				align : "center",
				sortable : false
			}, {
				name : 'fecreg',
				index : 'fecreg',
				width : 72,
				align : "center",
				sorttype : "date",
				sortable : false
			},{
				name : 'desest',
				index : 'desest',
				width : 100,
				align : "center",
				sortable : false
			}, {
				name : 'asunto',
				index : 'asunto',
				width : 150,
				align : "center",
				sorttype : "float",
				sortable : false
			}, ],

			rowNum : 10,
			rowList : [ 5, 10, 20 ],

			pager : '#pagerDocExtBus',
			//pginput : false,

			gridview : true,
			rownumbers : false,
			sortname : 'numdoc',
			viewrecords : false,
			multiselect:true,
			sortorder : 'asc',

			width : 673,
			height : '100%',

			gridComplete : function() {
			}

		});

	}
	
	function agregarExpediente(){
     	$("#expedienteAgregarForm").load("../upAgregarExpediente.htm", $("#expedienteAgregarForm").serialize(), function(){
     		$('#overlay').fadeIn('fast',function(){
                $('#box').animate({'top':'20%'},500);
            });
     	});
     	return false;
	}
	
	function agregarDocumentoExterno(){
     	$("#documentoExternoForm").load("../upAgregarDocumentoExt.htm", $("#documentoExternoForm").serialize(), function(){
     		$('#overlayExt').fadeIn('fast',function(){
                $('#boxExt').animate({'top':'20%'},500);
            });
     	});
     	return false;
	}
	

	function llenarGridDocumentoInterno(Json) {

		grid2.jqGrid({
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

					pager : '#pager2',
					//pginput : false,

					gridview : true,
					rownumbers : false,
					sortname : 'idInDocInterno',
					viewrecords : false,
					sortorder : 'asc',

					width : 673,
					height : '100%',

					gridComplete : function() {
					
					}

				});

	}
	
	$(function() {
        
		
		
		$('#btnGuardarExpediente').click(function(){
        	
    			jQuery("#frmBuscarExpediente").validationEngine('validate');
    	
		 		$('#frmBuscarExpediente').ajaxForm({url:"../saveExpediente.htm",type:"post", success:function(data){
		 					if(data == 'succes'){
		 						$("#grdDocExt").hide();
		 						limpiarBusquedaExpediente();
		 						var cldat = [];
		 						llenarGridDocumentoExterno(cldat);
								llenarGridDocumentoInterno(cldat);
		 						alert("Se registró correctamente el expediente");
		 					}else{
		 						if(data == 'error'){
		 							alert("Ocurrió un error al guardar los cambios , consulte con el administrador");
										
		 						}
		 					}
		 				}
	            });
				
			
	    });
	
	

		
        $('#btnBuscarExpediente').click(function(){
        	
        		jQuery("#frmBuscarExpediente").validationEngine('validate');
        	
		 		$('#frmBuscarExpediente').ajaxForm({url:"../searchExpediente.htm",type:"post", success:function(data){
		 		
		 					var dat = eval(data);
		 					if(dat.length == 0){
		 						$("#grdDocExt").hide();
		 						limpiarBusquedaExpediente();
		 						var cldat2 = [];
		 						llenarGridDocumentoExterno(cldat2);
								llenarGridDocumentoInterno(cldat2);
		 						alert("No se encontraron resultados para la busqueda");
		 					}else{
		 						if(dat.length == 1){
		 							 $("#hdnCodExpediente").val(dat[0].codexp);
									 $("#txtExpAnioBus").val(dat[0].anidoc); 
									 $("#txtExpNumeBus").val(dat[0].numdoc); 
									 $("#cmbTipoExpBus").val(dat[0].indexp);
									 $("#txtFechaIngresoBus").val(dat[0].fecing);
									 $("#txtCreadoPorBus").val(dat[0].codusu); 
									 $("#txtDocumentoBus").val(dat[0].desdoc);
									 $("#txtAsuntoBus").val(dat[0].desasu);
								     $("#txtInteresadoBus").val(dat[0].intere);
								     $("#txtUtdBus").val(dat[0].desutd);
								     $("#txtDetalleBus").val(dat[0].detall);
								     $("#txtDescripcionBus").val(dat[0].descri);
								     $("#txtNumFolBus").val(dat[0].numfol);
								     $("#txtDiasBus").val(dat[0].candia);
								     $("#cmbRemitenteBus").val(dat[0].codent);
								     
								    /* $("#txtExpAnioBus").attr('disabled',true);
								     $("#txtExpNumeBus").attr('disabled',true);
								     $("#cmbTipoExpBus").attr('disabled',true);*/
								     
								     $("#grdDocExt").show();
								     
								     var datjsonde = '${jsonListDocExt}';
								     alert(datjsonde);
								     alert(eval(datjsonde));
								     var j = JSON.parse(datjsonde);
								     alert(j);
								     llenarGridDocumentoExterno(datjsonde);
									 //llenarGridDocumentoInterno(hh);
										
		 						}else{
		 							alert("Se ha encontrado más de un registro , verifique");
		 						}
		 					
		 						//$("#expedienteListaForm").load("../upListarExpediente.htm",data, function(){
		 						/*$("#expedienteListaForm").html("<p>LISTAAA</p>",data, function(){	
		 							$('#overlay').fadeIn('fast',function(){
		 								$('#box').animate({'top':'30%'},500);
		 								$('#title-header-popup').text("Lista Expediente");
		 							});  
		 				     	});*/
		 						//$('#frmBuscarExpediente').reload();				
							/*$('#box').animate({'top':'100px'},500,function(){
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
                        	});*/
                	
                	}
		 					}
                });
				
			
        });



    });
        
     function limpiarBusquedaExpediente(){
    	 $("#hdnCodExpediente").val("");
		 $("#txtExpAnioBus").val("2015"); 
		 $("#txtExpNumeBus").val(""); 
		 $("#cmbTipoExpBus").val("E");
		 $("#txtFechaIngresoBus").val("");
		 $("#txtCreadoPorBus").val(""); 
		 $("#txtDocumentoBus").val("");
		 $("#txtAsuntoBus").val("");
	     $("#txtInteresadoBus").val("");
	     $("#txtUtdBus").val("");
	     $("#txtDetalleBus").val("");
	     $("#txtDescripcionBus").val("");
	     $("#txtNumFolBus").val("");
	     $("#txtDiasBus").val("");
	     $("#cmbRemitenteBus").val("");   
     }
	
</script>

<!--[if lt IE 9]>
<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

</head>

<body>
<!--  	<div id="block-grid">-->
		
		
		<fieldset class="scheduler-border">
					<legend class="scheduler-border">Buscar Expediente</legend>
	 	<div id="container-search" style="padding: 0px 175px 0px 0px;"> 
			<form:form id="frmBuscarExpediente" enctype="multipart/form-data" class="scrollbar" method="post" modelAttribute="expedienteVOBuscar">
					<ul>
						<table>
							<thead>
								<tr>
									<td></td>
									<td>
										<div class="table-grid">
										<div class="tr-grid">
											<div class="td-grid">
												<form:input id="btnBuscarExpediente" path="" type="submit" value="Buscar" style="margin: 19px;" class="button-image-complete"/>
											</div>
											<div class="td-grid">
												<ul id="block-grid-right">
					                                <li>
					                                    <a href="#" onclick="return agregarExpediente();" id="container-button-add" style="margin: 19px;"><div id="button-add-left"></div><div id="button-add-center"><span class="icon-plus-button">Nuevo Expediente</span></div><div id="button-add-right"></div></a>
					                                </li>
					                            </ul>
											</div>
										</div>	
										</div>
									</td>	
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Expediente :</td>
									<td>
										<table>
											<tr>
												<td>
													<form:hidden id="hdnCodExpediente" path="codExpediente"/>
													<form:hidden id="hdnCodEstadoExpediente" path="codEstadoExpediente"/>
													<form:input type="number" value="2015" path="anioDocumento" style="width: 60px;" id="txtExpAnioBus"  min="2015" max="2040"/>
												
												</td>
												<td><form:input id="txtExpNumeBus" style="width: 40px;"  type="text" class="input-border" path="numDocumento" /></td>
												<td><form:select id="cmbTipoExpBus" path="indExpediente"  style="width: 60px;"  class="select-box">
														<form:option value="E" label="E" />
														<form:option value="I" label="I" />
												</form:select></td>
											</tr>
										</table>
									</td>
									<td>Fecha de Ingreso:</td>
									<td><form:input id="txtFechaIngresoBus" path="fecCreacion" style="width: 80px;" type="text" disabled="true" /></td>
									<td>Creado Por:</td>
									<td><form:input id="txtCreadoPorBus" path="codUsuario" disabled="true" style="width: 80px;" type="text" class="input-border"/></td>
								</tr>
								<tr>
									<td>Documento:</td>
									<td colspan="5"><form:input id="txtDocumentoBus" path="desDocumento" style="width: 500px;" type="text" class="validate[required] input-border" /></td>
								</tr>
								<tr>
									<td>Remitentes:</td>
									<td colspan="5">
										<form:select id="cmbRemitenteBus" path="codEntidad"  disabled="false"
														class="select-box">
															<form:option value="1" label="SUNAT" />
															<form:option value="2" label="MEF" />
															<form:option value="3" label="MINISTERIO VIVIENDA" />
													</form:select>
									</td>
								</tr>
								<tr>
									<td>Asunto:</td>
									<td><form:input id="txtAsuntoBus" path="desAsunto" style="width: 200px;" type="text" class="input-border"/></td>
									<td>Interesado:</td>
									<td colspan="3"><form:input path="interesado" id="txtInteresadoBus" style="width: 200px;" type="text" class="input-border"/></td>
								</tr>
								<tr>
									<td>UTD:</td>
									<td><form:input id="txtUtdBus" path="desUtd" style="width: 200px;" type="text" class="input-border"/></td>
									<td>Detalle:</td>
									<td colspan="3"><form:input id="txtDetalleBus" path="detalle" style="width: 200px;" type="text" class="input-border"/></td>
								</tr>
								<tr>
									<td>Descripción:</td>
									<td><form:textarea id="txtDescripcionBus" path="descripcion" rows="4" cols="20"></form:textarea></td>
									<td>Nro.Folios:</td>
									<td><form:input type="number" value="1" style="width: 60px;" id="txtNumFolBus" path="numFolios" min="0" max="100" /></td>
									<td>Dias:</td>
									<td><form:input id="txtDiasBus" style="width: 70px;" type="text" class="input-border" path="canDias" /></td>
								</tr>

							</tbody>
						</table>

					</ul>
					
		<!-- Grilla -->
		<fieldset class="scheduler-border">
			<legend class="scheduler-border"> Documentos Externos </legend>
			<table id="listDocExtBus" />
			<div id="pagerDocExtBus" />
			<!-- Find Grilla -->
			<div id="container-grid">
				<div class="table-grid" style="margin: 0 0 0 0;">
					<div class="tr-grid">
						<div id="ResultadoBusqueda" class="td-grid" >
							<p style="color: #f7f7f7">Resultados de la b&uacute;squeda...</p>
						</div>
						<div class="td-grid" ></div>
					</div>
					<div id="grdDocExt" class="tr-grid">
						<div class="td-grid" style="width: 240px;">
							<ul id="block-grid-right">
                                <li>
                                    <a href="#" onclick = "return agregarDocumentoExterno();" id="container-button-add" style="margin: 1px;"><div id="button-add-left"></div><div id="button-add-center"><span class="icon-plus-button">Añadir Documento</span></div><div id="button-add-right"></div></a>
                                </li>
                            </ul>
						</div>
						<div class="td-grid" style="width: 250px;">
							<ul id="block-grid-right">
                                <li>
                                    <a onclick = "linkUp() " id="container-button-add" style="margin: 1px;"><div id="button-add-left"></div><div id="button-add-center"><span class="icon-plus-button">Eliminar Documento</span></div><div id="button-add-right"></div></a>
                                </li>
                            </ul>
						</div>
					</div>
				</div>
			</div>
		</fieldset>
		
		<fieldset class="scheduler-border">
			<legend class="scheduler-border">Documentos Internos</legend>
			<table id="list2"></table>
			<div id="pager2"></div>

			<div id="container-grid">
				<div class="table-grid">
					<div class="tr-grid">
						<div id="ResultadoBusqueda" class="td-grid">
							<p style="color: #f7f7f7">Resultados de la b&uacute;squeda...</p>
						</div>
					</div>
				</div>

			</div>

		</fieldset>
		
		
								<div class="table-grid">
										<div class="tr-grid">
											<div class="td-grid">
												<form:input id="btnGuardarExpediente" path="" type="submit" value="Grabar" style="margin: 19px;" class="button-image-complete"/>
											</div>
											<div class="td-grid">
												<form:input id="btnCancelarExpediente" path="" type="submit" value="Cancelar" style="margin: 19px;" class="button-image-complete"/>
											</div>
										</div>	
								</div>
										
			</form:form>
			
		
		
		 </div> 
</fieldset>
		
	



		
	<!--  </div>-->

	<div id="up" class="up"></div>
	<div id="expedienteAgregarForm"></div>
	<div id="expedienteListaForm"></div>
	<div id="documentoExternoForm"></div>
	

</body>
</html>

