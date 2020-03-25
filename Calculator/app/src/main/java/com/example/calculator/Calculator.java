package com.example.calculator;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

class Calculator
{
    /**
     * 현재 눌려진 button text
     */
    private StringBuilder buttonText;

    /**
     * 현재 입력된 텍스트 정보
     */
    private TextView textView;

    /**
     * 피연산자1
     */
    private float operand1 = 0;

    /**
     * 피연산자2
     */
    private float operand2 = 0;

    /**
     * 연산자
     */
    private String operator="";

    /**
     * 전 데이터
     */
    private String beforeData = "";

    /**
     * 계산결과
     */
    private float result = 0;

    private boolean isClear = false;


    static private final ArrayList<String> actBtnList = new ArrayList<>(Arrays.asList("AC", "DEL", "=","."));

    static private final ArrayList<String> numberList = new ArrayList<>(Arrays.asList("0","1","2","3","4","5","6","7","8","9","0","00"));

    static private final ArrayList<String> operatorList = new ArrayList<>(Arrays.asList("%","÷","×","-","+"));

    public void setButtonText(StringBuilder buttonText) {
        this.buttonText = buttonText;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public String getTextView() {
        String currentText = this.textView.getText().toString();
        return (currentText.length() > 0) ? currentText : null;
    }

    public void doAction() {
        String currentButtonText = buttonText.toString();
        if (numberList.contains(currentButtonText)) {
            if (isClear) {
                textView.setText("");
                isClear = false;
            }
            textView.append(currentButtonText);
            beforeData = currentButtonText;
        }
        else if (actBtnList.contains(currentButtonText)) {
            doAction(currentButtonText);

        }
        else if (operatorList.contains(currentButtonText)) {
            if ("".equals(textView.getText().toString()) == false) {
                if (numberList.contains(beforeData)) {
                    float currentViewNumber = Float.parseFloat(textView.getText().toString());
                    setOperand(currentViewNumber);
                }
            }
            // operand1 , operand2, operator 가 다있으면 계산한다.
            if (isAvailableCalculation()) {
                setOperand(tryCalculation());
            }
            setOperator(currentButtonText);
            beforeData = currentButtonText;
        }

    }

    private void doAction(String actionBtn) {
        if (textView == null) return;
        StringBuilder currentText = new StringBuilder(textView.getText());
        switch (actionBtn.toUpperCase()) {
            case "AC" : {
                textView.setText("");
                init();
                break;
            }
            case "DEL" : {
                if (currentText.length() > 0) {
                    currentText.deleteCharAt(textView.length()-1);
                    textView.setText(currentText);
                }
                break;
            }
            case "=" : {
                if (currentText.length() > 0 && this.operand1 != 0) {
                    //operand2 setting
                    setOperand(Float.parseFloat(currentText.toString()));
                }
                if (isAvailableCalculation()) {
                    tryCalculation();
                    isClear = true;
                }
                break;
            }
            case "." : {
                    if ("".equals(currentText.toString()) == false) {
                        if (".".equals(beforeData) == false) {
                            float currentNumber = Float.parseFloat(currentText.toString());
                            if (currentNumber == Math.round(currentNumber)) {
                                textView.append(".");
                                beforeData = ".";
                            }
                        }
                    }

            }
        }
    }

    private void setOperator(String operator) {
        this.operator = operator;
//        isClear = true;
    }

    /**
     * 계산
     * @return 계산결과
     */
    public float tryCalculation() {
        float calResult = doCalc();
        if (Math.round(calResult) == calResult) textView.setText(String.valueOf((int)calResult));
        else textView.setText(String.valueOf(calResult));
        init();

        return calResult;
    }

    private float doCalc() {
        if ("%".equals(operator)) {
            return operand1 % operand2;
        }
        else if ("÷".equals(operator)) {
            return operand1 / operand2;
        }
        else if ("×".equals(operator)) {
            return operand1 * operand2;
        }
        else if ("-".equals(operator)) {
            return operand1 - operand2;
        }
        else if ("+".equals(operator)) {
            return operand1 + operand2;
        }
        return 0;
    }

    /**
     * 피연산자, 연산자 초기화
     */
    private void init() {
        operand1 = 0;
        operand2 = 0;
        operator = "";
    }

    /**
     * setting operand
     * @param operand 피연산자 파라미터
     */
    private void setOperand(float operand) {
        if (operand == 0) return;
        if (this.operand1 == 0) {
            this.operand1 = operand;
            isClear = true;
            return;
        }
        if (this.operand2 == 0) this.operand2 = operand;


    }

    /**
     * 계산 조건이 존재하는지 확인
     * @return boolean 계산 가능 여부
     */
    private boolean isAvailableCalculation() {
        if (this.operand1 !=0 && this.operand2 !=0 && "".equals(operator) == false) return true;
        return false;
    }





}