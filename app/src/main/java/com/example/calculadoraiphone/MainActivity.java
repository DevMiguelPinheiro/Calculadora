package com.example.calculadoraiphone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView resultadotv,solucaotv;
    MaterialButton btn_c,btn_Aparen,btn_Fparen,btn_dividir,btn_7,btn_8,btn_9,btn_multi,btn_4,btn_5,btn_6,btn_sub,btn_1,btn_2,btn_3,btn_soma,btn_0,btn_virgula,btn_igual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultadotv = findViewById(R.id.resultadotv);
        solucaotv = findViewById(R.id.solucaotv);

        verificarBotao(btn_c,R.id.btn_c);
        verificarBotao(btn_Aparen,R.id.btn_Aparen);
        verificarBotao(btn_Fparen,R.id.btn_Fparen);
        verificarBotao(btn_dividir,R.id.btn_dividir);
        verificarBotao(btn_7,R.id.btn_7);
        verificarBotao(btn_8,R.id.btn_8);
        verificarBotao(btn_9,R.id.btn_9);
        verificarBotao(btn_4,R.id.btn_4);
        verificarBotao(btn_5,R.id.btn_5);
        verificarBotao(btn_6,R.id.btn_6);
        verificarBotao(btn_sub,R.id.btn_sub);
        verificarBotao(btn_1,R.id.btn_1);
        verificarBotao(btn_2,R.id.btn_2);
        verificarBotao(btn_3,R.id.btn_3);
        verificarBotao(btn_soma,R.id.btn_soma);
        verificarBotao(btn_0,R.id.btn_0);
        verificarBotao(btn_virgula,R.id.btn_virgula);
        verificarBotao(btn_igual,R.id.btn_igual);

    }

    void verificarBotao(MaterialButton btn, int id){

        btn = findViewById(id);
        btn.setOnClickListener(this);

    }
    //coletando os dados para calcular e setando Tv
    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solucaotv.getText().toString();


        //caso =  seja  pressionado
        if(buttonText.equals("=")){
            solucaotv.setText(resultadotv.getText());
            return;
        }
        //caso C seja pressionado
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        solucaotv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Erro")){
            resultadotv.setText(finalResult);
        }

    }

    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Erro";
        }
    }

}
    }
}