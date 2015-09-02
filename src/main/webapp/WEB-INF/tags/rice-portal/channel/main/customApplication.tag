<%--
  ~ Copyright 2006-2012 The Kuali Foundation
  ~
  ~ Licensed under the Educational Community License, Version 2.0 (the "License");
  ~ you may not use this file except in compliance with the License.
  ~ You may obtain a copy of the License at
  ~
  ~ http://www.opensource.org/licenses/ecl2.php
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  --%>

<%--
Kuali Rice ArcheType Help

This file contains custom application specific portal content.
--%>

<%@ include file="/rice-portal/jsp/sys/riceTldHeader.jsp" %>

<channel:portalChannelTop channelTitle="Kuali IA Dashboard"/>
<div class="body">

  <!--<ul class="chan">
      <li><portal:portalLink displayTitle="true" title="KAUST Faculty" 
      		url="${ConfigProperties.application.url}/kr-krad/lookup?viewId=PrincipalInvestigator-LookupView&returnLocation=${ConfigProperties.application.url}/portal.do" /></li>
      <li><portal:portalLink displayTitle="true" title="Awards" 
      		url="${ConfigProperties.application.url}/kr-krad/lookup?viewId=OcrfAward-LookupView&returnLocation=${ConfigProperties.application.url}/portal.do" /></li>		
  	  <li><portal:portalLink displayTitle="true" title="Contracts" 
      		url="${ConfigProperties.application.url}/kr-krad/lookup?viewId=Contract-LookupView&returnLocation=${ConfigProperties.application.url}/portal.do" /></li>
      <li><portal:portalLink displayTitle="true" title="Patents" 
      		url="${ConfigProperties.application.url}/kr-krad/lookup?viewId=PatentData-LookupView&returnLocation=${ConfigProperties.application.url}/portal.do" /></li>
      <li><portal:portalLink displayTitle="true" title="Publications" 
      		url="${ConfigProperties.application.url}/kr-krad/lookup?viewId=PublicationData-LookupView&returnLocation=${ConfigProperties.application.url}/portal.do" /></li>
  </ul>-->

  <table style="width:100%" >
   <tr>
      <td><portal:portalLink displayTitle="true" title="KAUST Faculty" 
      		url="${ConfigProperties.application.url}/kr-krad/lookup?viewId=PrincipalInvestigator-LookupView&returnLocation=${ConfigProperties.application.url}/portal.do" /></td>
      <td><portal:portalLink displayTitle="true" title="Awards" 
      		url="${ConfigProperties.application.url}/kr-krad/lookup?viewId=OcrfAward-LookupView&returnLocation=${ConfigProperties.application.url}/portal.do" /></td>		
  	  <td><portal:portalLink displayTitle="true" title="Contracts" 
      		url="${ConfigProperties.application.url}/kr-krad/lookup?viewId=Contract-LookupView&returnLocation=${ConfigProperties.application.url}/portal.do" /></td>
      <td><portal:portalLink displayTitle="true" title="Patents" 
      		url="${ConfigProperties.application.url}/kr-krad/lookup?viewId=PatentData-LookupView&returnLocation=${ConfigProperties.application.url}/portal.do" /></td>
      <td><portal:portalLink displayTitle="true" title="Publications" 
      		url="${ConfigProperties.application.url}/kr-krad/lookup?viewId=PublicationData-LookupView&returnLocation=${ConfigProperties.application.url}/portal.do" /></td>
   	  <td><portal:portalLink displayTitle="true" title="Scopus Search" 
      		url="${ConfigProperties.application.url}/kr-krad/scopusSearch?viewId=ScopusSearchView&returnLocation=${ConfigProperties.application.url}/portal.do" /></td>
   </tr>
  </table>
  
</div>
<channel:portalChannelBottom/>
