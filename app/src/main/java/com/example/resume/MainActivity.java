package com.example.resume;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText experienceEditText;
    private Spinner englishKnowledgeSpinner;
    private Spinner educationSpinner;
    private Button submitButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        experienceEditText = findViewById(R.id.experienceEditText);
        englishKnowledgeSpinner = findViewById(R.id.englishKnowledgeSpinner);
        educationSpinner = findViewById(R.id.educationSpinner);
        submitButton = findViewById(R.id.submitButton);
        resultTextView = findViewById(R.id.resultTextView);

        // Наполняем выпадающие списки данными
        ArrayAdapter<String> englishAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, new String[]{"A1", "A2", "B1", "B2", "C1", "C2"});
        englishAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        englishKnowledgeSpinner.setAdapter(englishAdapter);

        ArrayAdapter<String> educationAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, new String[]{"Основное общее", "Среднее общее", "СПО", "Бакалавриат", "Магистратура/Специалитет"});
        educationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        educationSpinner.setAdapter(educationAdapter);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                int experience = Integer.parseInt(experienceEditText.getText().toString());
                String englishKnowledge = englishKnowledgeSpinner.getSelectedItem().toString();
                String education = educationSpinner.getSelectedItem().toString();

                boolean isQualified = checkQualification(experience, englishKnowledge, education);
                String result = isQualified ? "Кандидат соответствует минимальным требованиям" :
                        "Кандидат не соответствует минимальным требованиям";

                resultTextView.setText(result);
            }
        });
    }



    private boolean checkQualification(int experience, String englishKnowledge, String education) {
        // Проверка на минимальный стаж, уровень английского и образование
        int minExperience = 1; // Минимальный требуемый стаж
        String[] requiredEducationLevels = {"Бакалавриат", "Магистратура/Специалитет"}; // Требуемые уровни образования

        // Проверяем, соответствует ли кандидат минимальным требованиям
        return (experience >= minExperience &&
                Arrays.asList(requiredEducationLevels).contains(education) &&
                englishKnowledge.compareTo("B2") >= 0);
    }
}
