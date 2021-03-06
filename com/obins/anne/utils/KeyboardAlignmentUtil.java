package com.obins.anne.utils;

import com.obins.anne.C0182R;
import java.util.ArrayList;
import java.util.List;

public class KeyboardAlignmentUtil {
    public static KeyboardAlignment keyboardAlignment1 = new KeyboardAlignment();
    public static KeyboardAlignment keyboardAlignment2 = new KeyboardAlignment();
    public static KeyboardAlignment keyboardAlignment3 = new KeyboardAlignment();

    static {
        keyboardAlignment1.name = AppUtils.appUtils.getResources().getString(C0182R.string.layout_default_1);
        keyboardAlignment1.content = AppUtils.appUtils.getResources().getString(C0182R.string.layout_default_1_desc);
        keyboardAlignment1.type = KeyboardAlignment.NORMAL_TYPE;
        List<KeyObject> standardKey_List = new ArrayList();
        standardKey_List.add(KeyObjectUtil.keyObjectESC);
        standardKey_List.add(KeyObjectUtil.keyObject1);
        standardKey_List.add(KeyObjectUtil.keyObject2);
        standardKey_List.add(KeyObjectUtil.keyObject3);
        standardKey_List.add(KeyObjectUtil.keyObject4);
        standardKey_List.add(KeyObjectUtil.keyObject5);
        standardKey_List.add(KeyObjectUtil.keyObject6);
        standardKey_List.add(KeyObjectUtil.keyObject7);
        standardKey_List.add(KeyObjectUtil.keyObject8);
        standardKey_List.add(KeyObjectUtil.keyObject9);
        standardKey_List.add(KeyObjectUtil.keyObject0);
        standardKey_List.add(KeyObjectUtil.keyObjectMinus);
        standardKey_List.add(KeyObjectUtil.keyObjectPlus);
        standardKey_List.add(KeyObjectUtil.keyObjectBackspace);
        standardKey_List.add(KeyObjectUtil.keyObjectTAB);
        standardKey_List.add(KeyObjectUtil.keyObjectQ);
        standardKey_List.add(KeyObjectUtil.keyObjectW);
        standardKey_List.add(KeyObjectUtil.keyObjectE);
        standardKey_List.add(KeyObjectUtil.keyObjectR);
        standardKey_List.add(KeyObjectUtil.keyObjectT);
        standardKey_List.add(KeyObjectUtil.keyObjectY);
        standardKey_List.add(KeyObjectUtil.keyObjectU);
        standardKey_List.add(KeyObjectUtil.keyObjectI);
        standardKey_List.add(KeyObjectUtil.keyObjectO);
        standardKey_List.add(KeyObjectUtil.keyObjectP);
        standardKey_List.add(KeyObjectUtil.keyObjectLBrackets);
        standardKey_List.add(KeyObjectUtil.keyObjectRBrackets);
        standardKey_List.add(KeyObjectUtil.keyObjectVertical);
        standardKey_List.add(KeyObjectUtil.keyObjectCAPS);
        standardKey_List.add(KeyObjectUtil.keyObjectA);
        standardKey_List.add(KeyObjectUtil.keyObjectS);
        standardKey_List.add(KeyObjectUtil.keyObjectD);
        standardKey_List.add(KeyObjectUtil.keyObjectF);
        standardKey_List.add(KeyObjectUtil.keyObjectG);
        standardKey_List.add(KeyObjectUtil.keyObjectH);
        standardKey_List.add(KeyObjectUtil.keyObjectJ);
        standardKey_List.add(KeyObjectUtil.keyObjectK);
        standardKey_List.add(KeyObjectUtil.keyObjectL);
        standardKey_List.add(KeyObjectUtil.keyObjectColon);
        standardKey_List.add(KeyObjectUtil.keyObjectQuotes);
        standardKey_List.add(KeyObjectUtil.keyObjectENTER);
        standardKey_List.add(KeyObjectUtil.keyObjectLSHIFT);
        standardKey_List.add(KeyObjectUtil.keyObjectZ);
        standardKey_List.add(KeyObjectUtil.keyObjectX);
        standardKey_List.add(KeyObjectUtil.keyObjectC);
        standardKey_List.add(KeyObjectUtil.keyObjectV);
        standardKey_List.add(KeyObjectUtil.keyObjectB);
        standardKey_List.add(KeyObjectUtil.keyObjectN);
        standardKey_List.add(KeyObjectUtil.keyObjectM);
        standardKey_List.add(KeyObjectUtil.keyObjectLessThan);
        standardKey_List.add(KeyObjectUtil.keyObjectBigThan);
        standardKey_List.add(KeyObjectUtil.keyObjectQuestion);
        standardKey_List.add(KeyObjectUtil.keyObjectRSHIFT);
        standardKey_List.add(KeyObjectUtil.keyObjectLCTRL);
        standardKey_List.add(KeyObjectUtil.keyObjectLWin);
        standardKey_List.add(KeyObjectUtil.keyObjectLALT);
        standardKey_List.add(KeyObjectUtil.keyObjectSPACE);
        standardKey_List.add(KeyObjectUtil.keyObjectRALT);
        standardKey_List.add(KeyObjectUtil.keyObjectFN);
        standardKey_List.add(KeyObjectUtil.keyObjectANNE);
        standardKey_List.add(KeyObjectUtil.keyObjectRCTRL);
        keyboardAlignment1.standardKey_List = standardKey_List;
        List<KeyObject> funKey_List = new ArrayList();
        funKey_List.add(KeyObjectUtil.keyObjectTilde);
        funKey_List.add(KeyObjectUtil.keyObjectF1);
        funKey_List.add(KeyObjectUtil.keyObjectF2);
        funKey_List.add(KeyObjectUtil.keyObjectF3);
        funKey_List.add(KeyObjectUtil.keyObjectF4);
        funKey_List.add(KeyObjectUtil.keyObjectF5);
        funKey_List.add(KeyObjectUtil.keyObjectF6);
        funKey_List.add(KeyObjectUtil.keyObjectF7);
        funKey_List.add(KeyObjectUtil.keyObjectF8);
        funKey_List.add(KeyObjectUtil.keyObjectF9);
        funKey_List.add(KeyObjectUtil.keyObjectF10);
        funKey_List.add(KeyObjectUtil.keyObjectF11);
        funKey_List.add(KeyObjectUtil.keyObjectF12);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectUp);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectUp);
        funKey_List.add(KeyObjectUtil.keyObjectScrollLock);
        funKey_List.add(KeyObjectUtil.keyObjectPause);
        funKey_List.add(KeyObjectUtil.keyObjectHome);
        funKey_List.add(KeyObjectUtil.keyObjectEnd);
        funKey_List.add(KeyObjectUtil.keyObjectPrintScreen);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectLeft);
        funKey_List.add(KeyObjectUtil.keyObjectDown);
        funKey_List.add(KeyObjectUtil.keyObjectRight);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectLeft);
        funKey_List.add(KeyObjectUtil.keyObjectDown);
        funKey_List.add(KeyObjectUtil.keyObjectRight);
        funKey_List.add(KeyObjectUtil.keyObjectPageUp);
        funKey_List.add(KeyObjectUtil.keyObjectPageDown);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectInsert);
        funKey_List.add(KeyObjectUtil.keyObjectDelete);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectWinLock);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectFN);
        funKey_List.add(KeyObjectUtil.keyObjectANNE);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        keyboardAlignment1.funKey_List = funKey_List;
        keyboardAlignment2.name = AppUtils.appUtils.getResources().getString(C0182R.string.layout_default_2);
        keyboardAlignment2.content = AppUtils.appUtils.getResources().getString(C0182R.string.layout_default_2_desc);
        keyboardAlignment2.type = KeyboardAlignment.NORMAL_TYPE;
        standardKey_List = new ArrayList();
        standardKey_List.add(KeyObjectUtil.keyObjectESC);
        standardKey_List.add(KeyObjectUtil.keyObject1);
        standardKey_List.add(KeyObjectUtil.keyObject2);
        standardKey_List.add(KeyObjectUtil.keyObject3);
        standardKey_List.add(KeyObjectUtil.keyObject4);
        standardKey_List.add(KeyObjectUtil.keyObject5);
        standardKey_List.add(KeyObjectUtil.keyObject6);
        standardKey_List.add(KeyObjectUtil.keyObject7);
        standardKey_List.add(KeyObjectUtil.keyObject8);
        standardKey_List.add(KeyObjectUtil.keyObject9);
        standardKey_List.add(KeyObjectUtil.keyObject0);
        standardKey_List.add(KeyObjectUtil.keyObjectMinus);
        standardKey_List.add(KeyObjectUtil.keyObjectPlus);
        standardKey_List.add(KeyObjectUtil.keyObjectBackspace);
        standardKey_List.add(KeyObjectUtil.keyObjectTAB);
        standardKey_List.add(KeyObjectUtil.keyObjectQ);
        standardKey_List.add(KeyObjectUtil.keyObjectW);
        standardKey_List.add(KeyObjectUtil.keyObjectE);
        standardKey_List.add(KeyObjectUtil.keyObjectR);
        standardKey_List.add(KeyObjectUtil.keyObjectT);
        standardKey_List.add(KeyObjectUtil.keyObjectY);
        standardKey_List.add(KeyObjectUtil.keyObjectU);
        standardKey_List.add(KeyObjectUtil.keyObjectI);
        standardKey_List.add(KeyObjectUtil.keyObjectO);
        standardKey_List.add(KeyObjectUtil.keyObjectP);
        standardKey_List.add(KeyObjectUtil.keyObjectLBrackets);
        standardKey_List.add(KeyObjectUtil.keyObjectRBrackets);
        standardKey_List.add(KeyObjectUtil.keyObjectVertical);
        standardKey_List.add(KeyObjectUtil.keyObjectCAPS);
        standardKey_List.add(KeyObjectUtil.keyObjectA);
        standardKey_List.add(KeyObjectUtil.keyObjectS);
        standardKey_List.add(KeyObjectUtil.keyObjectD);
        standardKey_List.add(KeyObjectUtil.keyObjectF);
        standardKey_List.add(KeyObjectUtil.keyObjectG);
        standardKey_List.add(KeyObjectUtil.keyObjectH);
        standardKey_List.add(KeyObjectUtil.keyObjectJ);
        standardKey_List.add(KeyObjectUtil.keyObjectK);
        standardKey_List.add(KeyObjectUtil.keyObjectL);
        standardKey_List.add(KeyObjectUtil.keyObjectColon);
        standardKey_List.add(KeyObjectUtil.keyObjectQuotes);
        standardKey_List.add(KeyObjectUtil.keyObjectENTER);
        standardKey_List.add(KeyObjectUtil.keyObjectLSHIFT);
        standardKey_List.add(KeyObjectUtil.keyObjectZ);
        standardKey_List.add(KeyObjectUtil.keyObjectX);
        standardKey_List.add(KeyObjectUtil.keyObjectC);
        standardKey_List.add(KeyObjectUtil.keyObjectV);
        standardKey_List.add(KeyObjectUtil.keyObjectB);
        standardKey_List.add(KeyObjectUtil.keyObjectN);
        standardKey_List.add(KeyObjectUtil.keyObjectM);
        standardKey_List.add(KeyObjectUtil.keyObjectLessThan);
        standardKey_List.add(KeyObjectUtil.keyObjectBigThan);
        standardKey_List.add(KeyObjectUtil.keyObjectUp);
        standardKey_List.add(KeyObjectUtil.keyObjectRSHIFT);
        standardKey_List.add(KeyObjectUtil.keyObjectLCTRL);
        standardKey_List.add(KeyObjectUtil.keyObjectANNE);
        standardKey_List.add(KeyObjectUtil.keyObjectLALT);
        standardKey_List.add(KeyObjectUtil.keyObjectSPACE);
        standardKey_List.add(KeyObjectUtil.keyObjectLeft);
        standardKey_List.add(KeyObjectUtil.keyObjectDown);
        standardKey_List.add(KeyObjectUtil.keyObjectRight);
        standardKey_List.add(KeyObjectUtil.keyObjectFN);
        keyboardAlignment2.standardKey_List = standardKey_List;
        funKey_List = new ArrayList();
        funKey_List.add(KeyObjectUtil.keyObjectTilde);
        funKey_List.add(KeyObjectUtil.keyObjectF1);
        funKey_List.add(KeyObjectUtil.keyObjectF2);
        funKey_List.add(KeyObjectUtil.keyObjectF3);
        funKey_List.add(KeyObjectUtil.keyObjectF4);
        funKey_List.add(KeyObjectUtil.keyObjectF5);
        funKey_List.add(KeyObjectUtil.keyObjectF6);
        funKey_List.add(KeyObjectUtil.keyObjectF7);
        funKey_List.add(KeyObjectUtil.keyObjectF8);
        funKey_List.add(KeyObjectUtil.keyObjectF9);
        funKey_List.add(KeyObjectUtil.keyObjectF10);
        funKey_List.add(KeyObjectUtil.keyObjectF11);
        funKey_List.add(KeyObjectUtil.keyObjectF12);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectUp);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectUp);
        funKey_List.add(KeyObjectUtil.keyObjectScrollLock);
        funKey_List.add(KeyObjectUtil.keyObjectPause);
        funKey_List.add(KeyObjectUtil.keyObjectHome);
        funKey_List.add(KeyObjectUtil.keyObjectEnd);
        funKey_List.add(KeyObjectUtil.keyObjectPrintScreen);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectLeft);
        funKey_List.add(KeyObjectUtil.keyObjectDown);
        funKey_List.add(KeyObjectUtil.keyObjectRight);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectLeft);
        funKey_List.add(KeyObjectUtil.keyObjectDown);
        funKey_List.add(KeyObjectUtil.keyObjectRight);
        funKey_List.add(KeyObjectUtil.keyObjectPageUp);
        funKey_List.add(KeyObjectUtil.keyObjectPageDown);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectInsert);
        funKey_List.add(KeyObjectUtil.keyObjectQuestion);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectANNE);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectFN);
        keyboardAlignment2.funKey_List = funKey_List;
        keyboardAlignment3.name = AppUtils.appUtils.getResources().getString(C0182R.string.layout_default_3);
        keyboardAlignment3.content = AppUtils.appUtils.getResources().getString(C0182R.string.layout_default_3_desc);
        keyboardAlignment3.type = KeyboardAlignment.NORMAL_TYPE;
        standardKey_List = new ArrayList();
        standardKey_List.add(KeyObjectUtil.keyObjectESC);
        standardKey_List.add(KeyObjectUtil.keyObject1);
        standardKey_List.add(KeyObjectUtil.keyObject2);
        standardKey_List.add(KeyObjectUtil.keyObject3);
        standardKey_List.add(KeyObjectUtil.keyObject4);
        standardKey_List.add(KeyObjectUtil.keyObject5);
        standardKey_List.add(KeyObjectUtil.keyObject6);
        standardKey_List.add(KeyObjectUtil.keyObject7);
        standardKey_List.add(KeyObjectUtil.keyObject8);
        standardKey_List.add(KeyObjectUtil.keyObject9);
        standardKey_List.add(KeyObjectUtil.keyObject0);
        standardKey_List.add(KeyObjectUtil.keyObjectMinus);
        standardKey_List.add(KeyObjectUtil.keyObjectPlus);
        standardKey_List.add(KeyObjectUtil.keyObjectBackspace);
        standardKey_List.add(KeyObjectUtil.keyObjectTAB);
        standardKey_List.add(KeyObjectUtil.keyObjectQ);
        standardKey_List.add(KeyObjectUtil.keyObjectW);
        standardKey_List.add(KeyObjectUtil.keyObjectE);
        standardKey_List.add(KeyObjectUtil.keyObjectR);
        standardKey_List.add(KeyObjectUtil.keyObjectT);
        standardKey_List.add(KeyObjectUtil.keyObjectY);
        standardKey_List.add(KeyObjectUtil.keyObjectU);
        standardKey_List.add(KeyObjectUtil.keyObjectI);
        standardKey_List.add(KeyObjectUtil.keyObjectO);
        standardKey_List.add(KeyObjectUtil.keyObjectP);
        standardKey_List.add(KeyObjectUtil.keyObjectLBrackets);
        standardKey_List.add(KeyObjectUtil.keyObjectRBrackets);
        standardKey_List.add(KeyObjectUtil.keyObjectVertical);
        standardKey_List.add(KeyObjectUtil.keyObjectCAPS);
        standardKey_List.add(KeyObjectUtil.keyObjectA);
        standardKey_List.add(KeyObjectUtil.keyObjectS);
        standardKey_List.add(KeyObjectUtil.keyObjectD);
        standardKey_List.add(KeyObjectUtil.keyObjectF);
        standardKey_List.add(KeyObjectUtil.keyObjectG);
        standardKey_List.add(KeyObjectUtil.keyObjectH);
        standardKey_List.add(KeyObjectUtil.keyObjectJ);
        standardKey_List.add(KeyObjectUtil.keyObjectK);
        standardKey_List.add(KeyObjectUtil.keyObjectL);
        standardKey_List.add(KeyObjectUtil.keyObjectColon);
        standardKey_List.add(KeyObjectUtil.keyObjectQuotes);
        standardKey_List.add(KeyObjectUtil.keyObjectENTER);
        standardKey_List.add(KeyObjectUtil.keyObjectLSHIFT);
        standardKey_List.add(KeyObjectUtil.keyObjectZ);
        standardKey_List.add(KeyObjectUtil.keyObjectX);
        standardKey_List.add(KeyObjectUtil.keyObjectC);
        standardKey_List.add(KeyObjectUtil.keyObjectV);
        standardKey_List.add(KeyObjectUtil.keyObjectB);
        standardKey_List.add(KeyObjectUtil.keyObjectN);
        standardKey_List.add(KeyObjectUtil.keyObjectM);
        standardKey_List.add(KeyObjectUtil.keyObjectLessThan);
        standardKey_List.add(KeyObjectUtil.keyObjectBigThan);
        standardKey_List.add(KeyObjectUtil.keyObjectQuestion);
        standardKey_List.add(KeyObjectUtil.keyObjectRSHIFT);
        standardKey_List.add(KeyObjectUtil.keyObjectLCTRL);
        standardKey_List.add(KeyObjectUtil.keyObjectLOption);
        standardKey_List.add(KeyObjectUtil.keyObjectLCmd);
        standardKey_List.add(KeyObjectUtil.keyObjectSPACE);
        standardKey_List.add(KeyObjectUtil.keyObjectRALT);
        standardKey_List.add(KeyObjectUtil.keyObjectFN);
        standardKey_List.add(KeyObjectUtil.keyObjectANNE);
        standardKey_List.add(KeyObjectUtil.keyObjectRCTRL);
        keyboardAlignment3.standardKey_List = standardKey_List;
        funKey_List = new ArrayList();
        funKey_List.add(KeyObjectUtil.keyObjectTilde);
        funKey_List.add(KeyObjectUtil.keyObjectF1);
        funKey_List.add(KeyObjectUtil.keyObjectF2);
        funKey_List.add(KeyObjectUtil.keyObjectF3);
        funKey_List.add(KeyObjectUtil.keyObjectF4);
        funKey_List.add(KeyObjectUtil.keyObjectF5);
        funKey_List.add(KeyObjectUtil.keyObjectF6);
        funKey_List.add(KeyObjectUtil.keyObjectF7);
        funKey_List.add(KeyObjectUtil.keyObjectF8);
        funKey_List.add(KeyObjectUtil.keyObjectF9);
        funKey_List.add(KeyObjectUtil.keyObjectF10);
        funKey_List.add(KeyObjectUtil.keyObjectF11);
        funKey_List.add(KeyObjectUtil.keyObjectF12);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectUp);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectUp);
        funKey_List.add(KeyObjectUtil.keyObjectScrollLock);
        funKey_List.add(KeyObjectUtil.keyObjectPause);
        funKey_List.add(KeyObjectUtil.keyObjectHome);
        funKey_List.add(KeyObjectUtil.keyObjectEnd);
        funKey_List.add(KeyObjectUtil.keyObjectPrintScreen);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectLeft);
        funKey_List.add(KeyObjectUtil.keyObjectDown);
        funKey_List.add(KeyObjectUtil.keyObjectRight);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectLeft);
        funKey_List.add(KeyObjectUtil.keyObjectDown);
        funKey_List.add(KeyObjectUtil.keyObjectRight);
        funKey_List.add(KeyObjectUtil.keyObjectPageUp);
        funKey_List.add(KeyObjectUtil.keyObjectPageDown);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectInsert);
        funKey_List.add(KeyObjectUtil.keyObjectDelete);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        funKey_List.add(KeyObjectUtil.keyObjectFN);
        funKey_List.add(KeyObjectUtil.keyObjectANNE);
        funKey_List.add(KeyObjectUtil.keyObjectReserved);
        keyboardAlignment3.funKey_List = funKey_List;
    }
}
