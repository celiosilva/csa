<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:template>
   <div class="well">
      <h1>Componentes Visuais</h1>
      <p>Os componentes visuais da arquitetura CSA são baseados em puro HTML, CSS e Javascript.
         Esses componentes são formados por vários frameworks e plugins Javascript utilizados
         conforme descritos abaixo.</p>
      <ol>
         <li>Informação - As informações da aplicação são exibidas em puro <strong>
               HTML/HTML5 </strong>.
         </li>
         <li>Aparencia - Toda a formatação e estilização é feita através do framework
            CSS/Javascript <strong> Bootstrap </strong>.
         </li>
         <li>Comportamento - Toda as funcionalidades e efeitos realizados no cliente são feitos
            através do framework <strong> jQuery </strong>, seus plugins e <strong>HTML5</strong>
         </li>
      </ol>
   </div>
   <div class="panel panel-success">
      <div class="panel-heading">
         <h2 class="panel-title">Campos para Entrada de Dados</h2>
      </div>
      <f:form cssClass="form-horizontal" commandName="camposEntrada" method="POST" role="form">
         <div class="panel-body">
            <div class="form-group">
               <label class="control-label col-lg-2" for="texto">Texto</label>
               <div class="col-lg-10">
                  <f:input path="texto" cssClass="form-control"
                     placeholder="Digite qualquer informação aqui!" />
               </div>
            </div>
            <div class="form-group">
               <label class="control-label col-lg-2" for="numerico">Numérico</label>
               <div class="col-lg-10">
                  <f:input path="numerico" cssClass="form-control"
                     placeholder="Aceita apenas números" />
               </div>
            </div>
            <div class="form-group">
               <label class="control-label col-lg-2" for="decimal">Decimal</label>
               <div class="col-lg-10">
                  <f:input path="decimal" cssClass="form-control"
                     placeholder="Números decimais (aceita , e .)" />
               </div>
            </div>
            <div class="form-group">
               <label class="control-label col-lg-2" for="data">Data</label>
               <div class="col-lg-10">
                  <f:input path="data" cssClass="form-control" placeholder="dd/mm/aaaa"
                     data-date-format="dd/mm/yyyy" />
               </div>
            </div>
            <div class="form-group">
               <label class="control-label col-lg-2" for="mascara">Máscara</label>
               <div class="col-lg-10">
                  <f:input path="mascara" cssClass="form-control"
                     placeholder="Campo com máscara para CEP" />
               </div>
            </div>
            <div class="form-group">
               <label class="control-label col-lg-2" for="monetario">Monetário</label>
               <div class="col-lg-10 input-group">
                  <span class="input-group-addon">R$</span>
                  <f:input path="monetario" cssClass="form-control" placeholder="Valor Monetário" />
               </div>
            </div>
            <div class="form-group">
               <label class="control-label col-lg-2" for="senha">Senha</label>
               <div class="col-lg-10">
                  <f:password path="senha" cssClass="form-control"
                     placeholder="Digite sua senha com mínimo de 6 caracteres" showPassword="true" />
               </div>
            </div>
            <div class="form-group">
               <label class="control-label col-lg-2" for="autocompletar">Autocompletar</label>
               <div class="col-lg-10">
                  <f:input path="autocompletar" cssClass="form-control"
                     placeholder="Digite algum caracter para buscar os dados" />
               </div>
            </div>
            <div class="form-group">
               <label class="control-label col-lg-2" for="lista">Lista</label>
               <div class="col-lg-10">
                  <f:select path="lista" itemLabel="descricao" items="${ufs}"
                     cssClass="form-control" />
               </div>
            </div>
            <div class="form-group">
               <label class="control-label col-lg-2" for="listaMultipla">Lista Múltipla</label>
               <div class="col-lg-10">
                  <f:select path="listaMultipla" cssClass="form-control" multiple="true"
                     data-placeholder="Selecione uma ou mais Unidades Federativas">
                     <f:options items="${ufs}" itemLabel="descricao" />
                  </f:select>
               </div>
            </div>
            <div class="form-group">
               <label class="control-label col-lg-2" for="checkbox">Checkbox</label>
               <div class="col-lg-10">
                  <div class="checkbox">
                     <label><f:checkbox path="checkbox" />Opção para seleção</label>
                  </div>
               </div>
            </div>
            <div class="form-group">
               <label class="control-label col-lg-2" for="checkboxMultiplo">Chk. Múltiplo</label>
               <div class="col-lg-10">
                  <c:forEach items="${ufs}" var="uf">
                     <label class="checkbox-inline"><f:checkbox path="checkboxMultiplo"
                           value="${uf}" />${uf.descricao}</label>
                  </c:forEach>
               </div>
            </div>
            <div class="form-group">
               <label class="control-label col-lg-2" for="selecao">Seleção</label>
               <div class="col-lg-10">
                  <c:forEach items="${ufs}" var="uf">
                     <div class="radio">
                        <label><f:radiobutton path="selecao" value="${uf}" />${uf.descricao}</label>
                     </div>
                  </c:forEach>
               </div>
            </div>
            <div class="form-group">
               <label class="control-label col-lg-2" for="areaTexto">Área de Texto</label>
               <div class="col-lg-10">
                  <f:textarea path="areaTexto" cssClass="form-control"
                     placeholder="Digite quanto texto quiser aqui!" rows="10" />
               </div>
            </div>
            <div class="form-group">
               <label class="control-label col-lg-2" for="file1">Imagens</label>
               <div class="col-lg-5">
                  <img id="imgUsuario" class="img-thumbnail"
                     src="${uploadManager.get(camposEntrada.imagem)}" style="width: 300px;" /> <input
                     id="file1" name="file" type="file" data-url="${root}/upload" />
                  <f:hidden path="imagem" />
               </div>
               <div class="col-lg-5">
                  <img id="imgUsuario_1" class="img-thumbnail"
                     src="${uploadManager.get(camposEntrada.imagem2)}" style="width: 300px;" /> <input
                     id="file2" name="file" type="file" data-url="${root}/upload" />
                  <f:hidden path="imagem2" />
               </div>
            </div>
            <div class="form-group">
               <label class="control-label col-lg-2" for="file3">Arquivos</label>
               <div class="col-lg-5">
                  <a id="linkArq1" href="${uploadManager.get(camposEntrada.arquivo1)}" download>Arquivo
                     1</a> <input id="file3" name="file" type="file" data-url="${root}/upload" />
                  <f:hidden path="arquivo1" />
               </div>
               <div class="col-lg-5">
                  <a id="linkArq2" href="${uploadManager.get(camposEntrada.arquivo2)}" download>Arquivo
                     2</a> <input id="file4" name="file" type="file" data-url="${root}/upload" />
                  <f:hidden path="arquivo2" />
               </div>
            </div>
         </div>
         <div class="panel-footer">
            <button class="btn btn-default">Cancelar</button>
            <input type="submit" class="btn btn-success" value="Salvar" /> <a href="#"
               class="btn btn-link">Exportar</a> <a href="#" class="btn btn-info">Voltar</a> <a
               href="<s:url value="/showcase/view/{model}">
               <s:param name="model" value="Gisele Bündchen"/>
            </s:url>">Gisele
               Bündchen (Veja o campo Texto)</a>
         </div>
      </f:form>
   </div>
   <script type="text/javascript">
                function converterDadosAutoCompletar(response) {
                    return {
                        suggestions : $.map(response, function(item) {
                            return {
                                value : item.label + ' (' + item.value + ')',
                                data : item.value
                            };
                        })
                    };
                }
                $(document).ready(
                        function() {
                            inputNumber('#numerico');
                            inputDecimal('#decimal');
                            inputDate('#data');
                            inputMask('#mascara', '99999-999');
                            inputMoney('#monetario');
                            inputAutocomplete('#autocompletar',
                                    '${root}/showcase/autocompleteufs',
                                    converterDadosAutoCompletar);
                            inputImage('#file1', '#imagem', '#imgUsuario');
                            inputImage('#file2', '#imagem2', '#imgUsuario_1');
                            inputFile('#file3', '#arquivo1', '#linkArq1');
                            inputFile('#file4', '#arquivo2', '#linkArq2');
                            select('#lista');
                            select('#listaMultipla');
                        });
            </script>
</t:template>
</html>