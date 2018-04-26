package me.dhamith.numbersystemcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button  btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnA, btnB, btnC,
            btnD, btnE, btnF, btnPlus , btnMinus, btnMult , btnDiv, btnEq, btnConv, btnCls, btnBack;
    private EditText txtBox1;
    private RadioButton rdoBin, rdoOct, rdoHex;
    private boolean onBin = true;
    private boolean onOct = false;
    private boolean onHex = false;
    private boolean conved = false;
    private String resultHold;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioGroup rdoGrp = findViewById(R.id.radioGroup);
        txtBox1 = findViewById(R.id.txtBox1);
        resultHold = "";
        initButtons();

        rdoGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
            int prevRdx = (onBin) ? 2 : (onOct) ? 8 : 16;
                if (!conved) {
                    switch (i) {
                        case R.id.rdoBin:
                            convert(prevRdx, 2);
                            toggleBinBtns(true);
                            toggleHexBtns(false);
                            toggleOctBtns(false);
                            onBin = true;
                            onOct = false;
                            onHex = false;
                            break;
                        case R.id.rdoOct:
                            convert(prevRdx, 8);
                            toggleBinBtns(true);
                            toggleOctBtns(true);
                            toggleHexBtns(false);
                            onBin = false;
                            onOct = true;
                            onHex = false;
                            break;
                        case R.id.rdoHex:
                            convert(prevRdx, 16);
                            toggleBinBtns(true);
                            toggleOctBtns(true);
                            toggleHexBtns(true);
                            onBin = false;
                            onOct = false;
                            onHex = true;
                            break;
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn0:
                txtBox1.append("0");
                break;

            case R.id.btn1:
                txtBox1.append("1");
                break;

            case R.id.btn2:
                txtBox1.append("2");
                break;

            case R.id.btn3:
                txtBox1.append("3");
                break;

            case R.id.btn4:
                txtBox1.append("4");
                break;

            case R.id.btn5:
                txtBox1.append("5");
                break;

            case R.id.btn6:
                txtBox1.append("6");
                break;

            case R.id.btn7:
                txtBox1.append("7");
                break;

            case R.id.btn8:
                txtBox1.append("8");
                break;

            case R.id.btn9:
                txtBox1.append("9");
                break;

            case R.id.btnA:
                txtBox1.append("A");
                break;

            case R.id.btnB:
                txtBox1.append("B");
                break;

            case R.id.btnC:
                txtBox1.append("C");
                break;

            case R.id.btnD:
                txtBox1.append("D");
                break;

            case R.id.btnE:
                txtBox1.append("E");
                break;

            case R.id.btnF:
                txtBox1.append("F");
                break;

            case R.id.btnPlus:
                txtBox1.append("+");
                break;

            case R.id.btnMinus:
                txtBox1.append("-");
                break;

            case R.id.btnMult:
                txtBox1.append("*");
                break;

            case R.id.btnDiv:
                txtBox1.append("/");
                break;

            case R.id.btnEq:
                calculate();
                break;

            case R.id.btnConv:
                convert();
                break;

            case R.id.btnCls:
                txtBox1.setText("");
                break;

            case R.id.btnBack:
                int length = txtBox1.getText().length();
                if (length > 0) {
                    txtBox1.getText().delete(length - 1, length);
                }
                break;

            default:
                break;
        }

    }

    private void calculate() {
        String input = txtBox1.getText().toString();
        if (!input.isEmpty()) {
            String type = (onBin) ? "bin" : (onOct) ? "oct" : "hex";
            try {
                StringParser parser = new StringParser(input.trim(), type);
                ExpressionTree tree = parser.getTree();
                txtBox1.setText(tree.getResult().toUpperCase());
            } catch (DivideByZeroException ex) {
                Toast.makeText(this, ex.getMessage(),
                        Toast.LENGTH_LONG).show();
            } catch (Exception ex) {
                Toast.makeText(this, "Invalid Operation! Try again.",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    private void convert() {
        String input = txtBox1.getText().toString();
        if (!input.isEmpty()) {
            int rdx = (onBin) ? 2 : (onOct) ? 8 : 16;
            try {
                if (!conved) {
                    String btnText = (onBin) ? "BIN" : (onOct) ? "OCT" : "HEX";
                    btnConv.setText(btnText);
                    toggleAllBtns(false, 0);
                    toggleRdoBtns(false);
                    conved = true;
                    long dec = Long.parseLong(input, rdx);
                    resultHold = input;
                    txtBox1.setText("DECIMAL: " + String.valueOf(dec));
                } else {
                    btnConv.setText("DEC");
                    toggleAllBtns(true, rdx);
                    toggleRdoBtns(true);
                    conved = false;
                    txtBox1.setText(resultHold);
                    resultHold = "";
                }
            } catch (Exception ex) {
                // Contains invalid chars
            }
        }
    }

    private void convert(int prevRdx, int curRdx) {
        String input = txtBox1.getText().toString();
        if (!input.isEmpty()) {
            try {
                long dec = Long.parseLong(input, prevRdx);
                resultHold = input;
                txtBox1.setText(Long.toString(dec, curRdx).toUpperCase());
            } catch (Exception ex) {
                // Either invalid input or an operation
                txtBox1.setText("");
            }
        }
    }

    private void initButtons() {
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);
        btnE = findViewById(R.id.btnE);
        btnF = findViewById(R.id.btnF);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMult = findViewById(R.id.btnMult);
        btnDiv = findViewById(R.id.btnDiv);
        btnEq = findViewById(R.id.btnEq);
        btnConv = findViewById(R.id.btnConv);
        btnCls = findViewById(R.id.btnCls);
        btnBack = findViewById(R.id.btnBack);

        rdoBin = findViewById(R.id.rdoBin);
        rdoOct = findViewById(R.id.rdoOct);
        rdoHex = findViewById(R.id.rdoHex);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnE.setOnClickListener(this);
        btnF.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMult.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnEq.setOnClickListener(this);
        btnConv.setOnClickListener(this);
        btnCls.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        toggleHexBtns(false);
        toggleOctBtns(false);
        onBin = true;
    }

    private void toggleHexBtns(boolean cond) {
        btn8.setEnabled(cond);
        btn9.setEnabled(cond);
        btnA.setEnabled(cond);
        btnB.setEnabled(cond);
        btnC.setEnabled(cond);
        btnD.setEnabled(cond);
        btnE.setEnabled(cond);
        btnF.setEnabled(cond);
    }

    private void toggleOctBtns(boolean cond) {
        btn2.setEnabled(cond);
        btn3.setEnabled(cond);
        btn4.setEnabled(cond);
        btn5.setEnabled(cond);
        btn6.setEnabled(cond);
        btn7.setEnabled(cond);
    }

    private void toggleOpBtns(boolean cond) {
        btnPlus.setEnabled(cond);
        btnMinus.setEnabled(cond);
        btnMult.setEnabled(cond);
        btnDiv.setEnabled(cond);
        btnEq.setEnabled(cond);
        btnCls.setEnabled(cond);
        btnBack.setEnabled(cond);
    }

    private void toggleBinBtns(boolean cond) {
        btn0.setEnabled(cond);
        btn1.setEnabled(cond);
    }

    private void toggleAllBtns(boolean cond, int rdx) {
        if (rdx == 0) {
            toggleBinBtns(cond);
            toggleOctBtns(cond);
            toggleHexBtns(cond);
        } if (rdx == 2) {
            toggleBinBtns(cond);
            toggleOctBtns(false);
            toggleHexBtns(false);
        } else if (rdx == 8) {
            toggleBinBtns(cond);
            toggleOctBtns(cond);
            toggleHexBtns(false);
        } else if (rdx == 16) {
            toggleBinBtns(cond);
            toggleOctBtns(cond);
            toggleHexBtns(cond);
        }
        toggleOpBtns(cond);
    }

    private void toggleRdoBtns(boolean cond) {
        rdoBin.setEnabled(cond);
        rdoOct.setEnabled(cond);
        rdoHex.setEnabled(cond);
    }
}
