package cn.reimbursement.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class FileUpload {
    /**
     * @function 多文件上传
     * @return
     */
    public static List<String> fileMany(MultipartFile[] files , String saveUrl, String fileType,String billId){
        List<String> picUrl = new LinkedList<>();
        String newUrl = saveUrl +"/"+billId+ "/";
        File saveDir = new File(newUrl);
        if(!saveDir.exists()){
            saveDir.mkdirs();
        }
        String newFileUrl = "";
        for(MultipartFile file : files){
            if(file != null){
                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                System.out.println(file.getOriginalFilename());
                String fileName = file.getOriginalFilename();
                newFileUrl = newUrl+fileName;
                File saveFile = new File(newFileUrl);
                System.out.println(saveFile);
                try {
                    file.transferTo(saveFile);
                    picUrl.add(newFileUrl);
                    System.out.println("上传成功");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("上传失败");
                }
            }
        }
        return picUrl;
    }
}
