package com.youngg209.utils;

import com.youngg209.dto.student.StudentSaveRequestDto;
import com.youngg209.dto.subject.SubjectSaveRequestDto;
import com.youngg209.exception.CommonBaseException;

import java.util.regex.Pattern;

public class Matches {
    private static String namePattern = "^[ㄱ-ㅎㅏ-ㅣ가-힣0-9a-zA-Z]*$";
    private static String phoneNumberPattern = "^\\d{3}-\\d{4}-\\d{4}$";
    private static String[] schoolTypePattern = {"ELEMENTARY", "MIDDLE", "HIGH"};

    public boolean isStudentMatches(StudentSaveRequestDto requestDto) {

        boolean isMatch = false;
        String name = requestDto.getName();
        int age = requestDto.getAge();
        String schoolType = requestDto.getSchoolType();
        String phoneNumber = requestDto.getPhoneNumber();

        if (name.length() > 16) {
            throw new CommonBaseException(ErrorStatus.NOT_MATCH_VAL,name);
        }
        if (!Pattern.matches(namePattern, name)) {
            throw new CommonBaseException(ErrorStatus.NOT_MATCH_VAL,name);
        }
        if (age <= 0 || age >= 20) {
            throw new CommonBaseException(ErrorStatus.NOT_MATCH_VAL,String.valueOf(age));
        }
        for (String type : schoolTypePattern) {
            if (type.equals(schoolType)) {
                isMatch = true;
            }
        }
        if (!isMatch){
            throw new CommonBaseException(ErrorStatus.NOT_MATCH_VAL,schoolType);
        }
        if (!Pattern.matches(phoneNumberPattern, phoneNumber)) {
            throw new CommonBaseException(ErrorStatus.NOT_MATCH_VAL,phoneNumber);
        }

        return true;
    }

    public boolean isSubjectMatches(SubjectSaveRequestDto requestDto) {

        String name = requestDto.getName();

        if (name.length() > 12) {
            throw new CommonBaseException(ErrorStatus.NOT_MATCH_VAL,name);
        }
        if (!Pattern.matches(namePattern, name)) {
            throw new CommonBaseException(ErrorStatus.NOT_MATCH_VAL,name);
        }

        return true;
    }
}
