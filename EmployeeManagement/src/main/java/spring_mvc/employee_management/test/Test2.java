package spring_mvc.employee_management.test;

import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import static org.bytedeco.opencv.global.opencv_imgcodecs.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*;

import javax.servlet.ServletContext;

import static org.bytedeco.opencv.global.opencv_core.*;
import static org.bytedeco.opencv.global.opencv_highgui.*;

public class Test2 {
    public static void main(String[] args) {
    	
    	

    	// 拼接资源路径
    	String resourcePath = "E:\\workspace\\EmployeeManagement\\src\\main\\webapp\\resources\\img\\qq.jpg";

    	// Load image
    	Mat img = imread(resourcePath);

    	// Convert image to grayscale
    	Mat gray = new Mat();
    	cvtColor(img, gray, COLOR_BGR2GRAY);

    	// Load cascade classifier
    	CascadeClassifier faceCascade = new CascadeClassifier("E:\\workspace\\EmployeeManagement\\src\\main\\webapp\\resources\\face_detect.xml");

    	// Detect faces
    	RectVector faceRect = new RectVector();
    	faceCascade.detectMultiScale(gray, faceRect, 1.1, 5, 0, new Size(), new Size());

    	// Output the number of detected faces
    	System.out.println("Number of faces detected: " + faceRect.size());



    }
    

}
