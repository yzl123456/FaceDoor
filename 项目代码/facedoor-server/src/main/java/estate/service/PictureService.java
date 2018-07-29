package estate.service;

import estate.exception.PictureUploadException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by 应泽林 on 18-1-22.
 */
public interface PictureService
{
    /**
     * 保存图片,并且返回保存后的图片id组成的字符串,逗号分隔
     * @param fileMap
     * @return
     */
    String saveAndReturnID(Map<String,MultipartFile> fileMap) throws PictureUploadException;


    /**
     * 通过图片id字符串返回图片路径字符串
     * @param ids
     * @param request
     * @return
     */
    String getPathsByIDs(String ids,HttpServletRequest request);

}
