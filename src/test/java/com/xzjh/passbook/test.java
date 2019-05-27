package com.xzjh.passbook;

import com.xzjh.passbook.entity.Merchants;
import com.xzjh.passbook.imports.MultipartImport;
import com.xzjh.passbook.service.IMerchantsServ;
import com.xzjh.passbook.vo.CreateMerchantsRequest;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//@SpringBootTest
public class test {



    @Autowired
    private MultipartImport multipartImport;

    @Autowired
    private IMerchantsServ merchantsServ;

    @Test
    public void upload()throws Exception{

        MultipartFile file = FileToMultipartFile(new File("D://merchants1111111111.xlsx"));

       List<Merchants> merchants = multipartImport.readXls(file);

        for (Merchants merchant:merchants) {
            CreateMerchantsRequest request = new CreateMerchantsRequest();
            request.setName(merchant.getName());
            request.setLogoUrl(merchant.getLogoUrl());
            request.setBusinessLicenseUrl(merchant.getBusinessLicenseUrl());
            request.setPhone(merchant.getPhone());
            request.setAddress(merchant.getAddress());
            merchantsServ.createMerchants(request);
        }

    }


    /**
     * <h1>File 转 MultipartFile</h1>
     * @param file
     * @return
     */
    public MultipartFile FileToMultipartFile(File file) throws Exception{
        //        MockMultipartFile mockMultipartFile    = new MockMultipartFile("test (copy).txt", new FileInputStream(file));
//        mockMultipartFile.transferTo(new File("D://2.txt"));
        String name = file.getName();
        MockMultipartFile mockMultipartFile = new MockMultipartFile(name,new FileInputStream(file));
//        mockMultipartFile.transferTo(new File("H://"+name));
        return  mockMultipartFile;

    }
}
