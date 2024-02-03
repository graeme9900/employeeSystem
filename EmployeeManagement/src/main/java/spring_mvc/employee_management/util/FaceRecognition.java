package spring_mvc.employee_management.util;

import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGR2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_RGB2BGR;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.opencv.core.CvType;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class FaceRecognition {
	public Boolean determineHumanFace(BufferedImage bufferedImage) {
		// 定义JPEG图像文件路径
        String filePath = "E:\\workspace\\EmployeeManagement\\src\\main\\webapp\\resources\\img\\output.jpg";

        // 使用JavaCV读取JPEG图像
        Mat mat = opencv_imgcodecs.imread(filePath);

        // 检查图像是否成功读取
        if (mat.empty()) {
            System.out.println("无法读取图像: " + filePath);
        } else {
            System.out.println("成功读取图像: " + filePath);
            // 这里可以进行图像处理或其他操作
        }


    	// Convert image to grayscale
    	Mat gray = new Mat();
    	cvtColor(mat, gray, COLOR_BGR2GRAY);

    	// Load cascade classifier
    	CascadeClassifier faceCascade = new CascadeClassifier("E:\\workspace\\EmployeeManagement\\src\\main\\webapp\\resources\\face_detect.xml");

    	// Detect faces
    	RectVector faceRect = new RectVector();
    	faceCascade.detectMultiScale(gray, faceRect, 1.1, 5, 0, new Size(), new Size());

    	// Output the number of detected faces
    	System.out.println("Number of faces detected: " + faceRect.size());
    	if (faceRect.size() == 1) {
    		return true;
    	} else {
    		return false;
    	}
    	
	}
	
	public BufferedImage decodeBase64(String base64ImageData) {
		// 去除"data:image/jpeg;base64,"前缀
        base64ImageData = base64ImageData.replace("data:image/jpeg;base64,", "");

        try {
            // 解码Base64字符串为字节数组
            byte[] imageBytes = Base64.getDecoder().decode(base64ImageData);

            // 将字节数组转换为BufferedImage对象
            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
            BufferedImage bufferedImage = ImageIO.read(bis);

            // 保存图像到文件
            File outputImage = new File("E:\\workspace\\EmployeeManagement\\src\\main\\webapp\\resources\\img\\output.jpg");
            ImageIO.write(bufferedImage, "jpg", outputImage);

//            System.out.println("图像已成功保存到文件: " + outputImage.getAbsolutePath());
            return bufferedImage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	 
	

	

	

	
}
