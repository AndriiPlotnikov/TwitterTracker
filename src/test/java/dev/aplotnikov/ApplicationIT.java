package dev.aplotnikov;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationIT {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void shouldStartSuccessfully() throws InterruptedException {
        // When
        Application.main(new String[] {});
        Thread.sleep(10000);
        String output = outputCapture.toString();

        // Then
        assertThat(output).contains("Started Application in");
        assertThat(output).contains("Connection established.");
    }
}
