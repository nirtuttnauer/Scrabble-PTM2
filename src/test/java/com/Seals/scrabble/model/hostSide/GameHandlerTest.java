//package com.Seals.scrabble.model.hostSide;
//
//import com.Seals.scrabble.model.hostSide.GameHandler;
//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class GameHandlerTest {
//
//    @Test
//    void testHandleClient() {
//        // Prepare test data
//        String testRequest = "Hello, Server!";
//        String expectedResponse = testRequest;
//
//        // Create input and output streams for testing
//        InputStream inputStream = new ByteArrayInputStream(testRequest.getBytes());
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//
//        // Create GameHandler instance
//        GameHandler gameHandler = new GameHandler();
//
//        // Call handleClient method
//        gameHandler.handleClient(inputStream, new PrintWriter(outputStream, true));
//
//        // Get the response from the output stream
//        String actualResponse = outputStream.toString().trim();
//
//        // Assert the response
//        assertEquals(expectedResponse, actualResponse);
//    }
//}
