
INSERT INTO KREW_DOC_HDR_S VALUES(0)
/
INSERT INTO KREW_DOC_TYP_T(DOC_TYP_ID, PARNT_ID, DOC_TYP_NM, DOC_TYP_VER_NBR, ACTV_IND, CUR_IND, LBL, 
    PREV_DOC_TYP_VER_NBR, DOC_TYP_DESC, DOC_HDLR_URL, POST_PRCSR, JNDI_URL, BLNKT_APPR_PLCY, ADV_DOC_SRCH_URL, 
    RTE_VER_NBR, NOTIFY_ADDR, APPL_ID, EMAIL_XSL, SEC_XML, VER_NBR, BLNKT_APPR_GRP_ID, RPT_GRP_ID, GRP_ID, 
    HELP_DEF_URL, OBJ_ID, DOC_SEARCH_HELP_URL, DOC_HDR_ID, AUTHORIZER) 
	VALUES(LAST_INSERT_ID(), 2681, 'ConferenceSession', 0, 1, 1, 'Conference Session', 
    NULL, NULL, '${kr.krad.url}/maintenance?methodToCall=docHandler', NULL, NULL, NULL, NULL, 
    '2', NULL, '', NULL, NULL, 1, NULL, NULL, NULL, 
    NULL, UUID(), NULL, NULL, NULL)
/
INSERT INTO KREW_DOC_HDR_S VALUES(0)
/
INSERT INTO KREW_DOC_TYP_T(DOC_TYP_ID, PARNT_ID, DOC_TYP_NM, DOC_TYP_VER_NBR, ACTV_IND, CUR_IND, LBL, 
    PREV_DOC_TYP_VER_NBR, DOC_TYP_DESC, DOC_HDLR_URL, POST_PRCSR, JNDI_URL, BLNKT_APPR_PLCY, ADV_DOC_SRCH_URL, 
    RTE_VER_NBR, NOTIFY_ADDR, APPL_ID, EMAIL_XSL, SEC_XML, VER_NBR, BLNKT_APPR_GRP_ID, RPT_GRP_ID, GRP_ID, 
    HELP_DEF_URL, OBJ_ID, DOC_SEARCH_HELP_URL, DOC_HDR_ID, AUTHORIZER) 
	VALUES(LAST_INSERT_ID(), 2681, 'PresenterInfo', 0, 1, 1, 'Presenter Information', 
    NULL, NULL, '${kr.krad.url}/maintenance?methodToCall=docHandler', NULL, NULL, NULL, NULL, 
    '2', NULL, '', NULL, NULL, 1, NULL, NULL, NULL, 
    NULL, UUID(), NULL, NULL, NULL)
/