package com.example.learnapp.tools;

import com.example.learnapp.exception.GradeSwitchException;

public class GradeUtils {

    public static int GradeSwith(String Grade) throws GradeSwitchException {
        switch (Grade){
            case "小一":
                return 1;
            case "小二":
                return 2;
            case "小三":
                return 3;
            case "小四":
                return 4;
            case "小五":
                return 5;
            case "小六":
                return 6;
            case "初一":
                return 7;
            case "初二":
                return 8;
            case "初三":
                return 9;
            case "高一":
                return 10;
            case "高二":
                return 11;
            case "高三":
                return 12;
            default:
                throw new GradeSwitchException("输入的年级不合法");
        }
    }


    public static int GradeCompare(String grade1, String grade2) throws GradeSwitchException {
            int grade1Level = GradeUtils.GradeSwith(grade1);
            int grade2Level = GradeUtils.GradeSwith(grade2);
            return grade1Level-grade2Level;
    }
}
