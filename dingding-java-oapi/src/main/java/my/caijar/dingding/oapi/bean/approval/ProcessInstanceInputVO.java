package my.caijar.dingding.oapi.bean.approval;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.request.OapiProcessinstanceCreateRequest;
import com.dingtalk.api.request.OapiProcessinstanceCreateRequest.FormComponentValueVo;
import my.caijar.dingding.oapi.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author CaiTao
 * @date 2018/08/22
 * 审批实例
 */
public class ProcessInstanceInputVO {

	/***
	 * 可以与VUE结合，这里是路由，可以和processCode组合一张配置表获取code
	 */
    private String routUrl = "";

    /***
     * 前端提交的表单数据—json格式。
     */
    private String jsonVo = "";


	/**
	 * 审批需要的额外参数。
	 * 主要是关于审批附件上传的参数。
	 * auth_code 钉钉授权码由前端获取
	 */
	private String params = "";

    /**
     * 审批人列表
     */
    private String approvers;

	/**
	 * 审批人列表
	 */
	private List<OapiProcessinstanceCreateRequest.ProcessInstanceApproverVo> approvers_v2;

    /**
     * 抄送人列表
     */
    private String ccList;

    /**
     * 抄送时机
     */
    private String ccPosition;

    /**
     * 审批发起人部门编码.
     */
    private String deptId;

    /**
     * 单行输入框、多行输入框的表单数据
     */
    private List<TextForm> textForms;

    /**
     * 图片表单数据
     */
    private List<PictureForm> pictureForms;

    /**
      * 内部联系人
      */
    private List<ContactForm> contactForms;

    /**
     * 明细表单数据
     */
    private List<DetailForm> detailForms;

    /**
     * 附件表单数据
     */
    private List<AppendixForm> appendixForms;

    /**
     * 审批发起人
     */
    private String originatorUserId;

    /**
     * 临时文件项目路径。针对业务数据明细生成的临时文件。
     */
    private String fileUrl;


    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public static class TextForm {
        /**
         * 表单控件名称
         */
        private String name;

        /**
         * 表单值
         */
        private String value = "";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class PictureForm {
        /**
         * 表单控件名称
         */
        private String name;

        /**
         * 表单值，数组格式
         */
        private List<String> value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getValue() {
            return value;
        }

        public void setValue(List<String> value) {
            this.value = value;
        }
    }

    /**
      * 内部联系人
      */
    public static class ContactForm {
        /**
         * 表单控件名称
         */
        private String name;

        /**
         * 表单值，数组格式
         */
        private List<String> value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getValue() {
            return value;
        }

        public void setValue(List<String> value) {
            this.value = value;
        }
    }

    /**
     * @Description 附件上传。。。附件映射实体。
     * @Author CaiTao
     * @Date 2019/8/8 14:40
     * @Version 1.0
     */
    public static class AppendixForm {
        /**
         * 表单控件名称
         */
        private String name;

        /**
         * 表单值，数组格式
         */
        private List<SpaceArray> attachmentArray;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<SpaceArray> getAttachmentArray() {
            return attachmentArray;
        }

        public void setAttachmentArray(List<SpaceArray> attachmentArray) {
            this.attachmentArray = attachmentArray;
        }
    }

    public static class SpaceArray {
        /**
         * 文件ID
         */
        private String fileId;

        /**
         * 文件名称
         */
        private String fileName;
        /**
         * 文件类型
         */
        private String fileType;
        /**
         * 文件钉盘空间id
         */
        private String spaceId;
        /**
         * 文件大小
         */
        private String fileSize;

        public String getFileId() {
            return fileId;
        }

        public void setFileId(String fileId) {
            this.fileId = fileId;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileType() {
            return fileType;
        }

        public void setFileType(String fileType) {
            this.fileType = fileType;
        }

        public String getSpaceId() {
            return spaceId;
        }

        public void setSpaceId(String spaceId) {
            this.spaceId = spaceId;
        }

        public String getFileSize() {
            return fileSize;
        }

        public void setFileSize(String fileSize) {
            this.fileSize = fileSize;
        }
    }


    public static class DetailForm {
        /**
         * 表单控件名称
         */
        private String name;

        /**
         * 明细里的文本控件列表
         */
        private List<TextForm> textForms;

        /**
         * 明细里的图片控件列表
         */
        private List<PictureForm> pictureForms;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<TextForm> getTextForms() {
            return textForms;
        }

        public void setTextForms(List<TextForm> textForms) {
            this.textForms = textForms;
        }

        public List<PictureForm> getPictureForms() {
            return pictureForms;
        }

        public void setPictureForms(List<PictureForm> pictureForms) {
            this.pictureForms = pictureForms;
        }
    }

    public String getApprovers() {
        return approvers;
    }

    public void setApprovers(String approvers) {
        this.approvers = approvers;
    }

    public String getCcList() {
        return ccList;
    }

    public void setCcList(String ccList) {
        this.ccList = ccList;
    }

    public String getCcPosition() {
        return ccPosition;
    }

    public void setCcPosition(String ccPosition) {
        this.ccPosition = ccPosition;
    }


    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public List<TextForm> getTextForms() {
        return textForms;
    }

    public void setTextForms(List<TextForm> textForms) {
        this.textForms = textForms;
    }

    public List<PictureForm> getPictureForms() {
        return pictureForms;
    }

    public void setPictureForms(List<PictureForm> pictureForms) {
        this.pictureForms = pictureForms;
    }

    public List<DetailForm> getDetailForms() {
        return detailForms;
    }

    public void setDetailForms(List<DetailForm> detailForms) {
        this.detailForms = detailForms;
    }

    public String getOriginatorUserId() {
        return originatorUserId;
    }

    public void setOriginatorUserId(String originatorUserId) {
        this.originatorUserId = originatorUserId;
    }

    public List<AppendixForm> getAppendixForms() {
        return appendixForms;
    }

    public void setAppendixForms(List<AppendixForm> appendixForms) {
        this.appendixForms = appendixForms;
    }

    public String getJsonVo() {
        return jsonVo;
    }

    public void setJsonVo(String jsonVo) {
        this.jsonVo = jsonVo;
    }

    public String getRoutUrl() {
        return routUrl;
    }

    public void setRoutUrl(String routUrl) {
        this.routUrl = routUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public List<ContactForm> getContactForms() {
        return contactForms;
    }

	public List<OapiProcessinstanceCreateRequest.ProcessInstanceApproverVo> getApprovers_v2() {
		return approvers_v2;
	}

	public void setApprovers_v2(List<OapiProcessinstanceCreateRequest.ProcessInstanceApproverVo> approvers_v2) {
		this.approvers_v2 = approvers_v2;
	}

	public void setContactForms(List<ContactForm> contactForms) {
        this.contactForms = contactForms;
    }

    /**
     * 生成FormComponentValueVo，用于调用发起审批实例的接口
     *
     * @return
     */
    public List<FormComponentValueVo> generateForms() {
        List<FormComponentValueVo> result = new ArrayList<FormComponentValueVo>();
        if (!ArrayUtil.isEmpty(textForms)) {
            for (TextForm textForm : textForms) {
                result.add(generateFormWithTextForm(textForm));
            }
        }

        if (!ArrayUtil.isEmpty(pictureForms)) {
            for (PictureForm pictureForm : pictureForms) {
                result.add(generateFormWithPictureForm(pictureForm));
            }
        }

        if (!ArrayUtil.isEmpty(detailForms)) {
            for (DetailForm detailForm : detailForms) {
                result.add(generateFormWithDetailForm(detailForm));
            }
        }

        if (!ArrayUtil.isEmpty(appendixForms)) {
            for (AppendixForm appendixForm : appendixForms) {
                result.add(generateFormWithAppendixForm(appendixForm));
            }
        }

        if(!ArrayUtil.isEmpty(contactForms)) {
            for (ContactForm contactForm : contactForms) {
                result.add(generateFormWithContactForm(contactForm));
            }
        }
        return result;
    }

    private FormComponentValueVo generateFormWithTextForm(TextForm textForm) {
        FormComponentValueVo form = new FormComponentValueVo();
        form.setName(textForm.getName());
        form.setValue(textForm.getValue());
        return form;
    }

    private FormComponentValueVo generateFormWithPictureForm(PictureForm pictureForm) {
        FormComponentValueVo form = new FormComponentValueVo();
        form.setName(pictureForm.getName());
        form.setValue(JSON.toJSONString(pictureForm.getValue()));
        return form;
    }

    private FormComponentValueVo generateFormWithContactForm(ContactForm contactForm) {
        FormComponentValueVo form = new FormComponentValueVo();
        form.setName(contactForm.getName());
        form.setValue(JSON.toJSONString(contactForm.getValue()));
        return form;
    }


    private FormComponentValueVo generateFormWithAppendixForm(AppendixForm appendixForm) {
        FormComponentValueVo form = new FormComponentValueVo();
        List<SpaceArray> spaceArrays = appendixForm.getAttachmentArray();
        JSONArray array = new JSONArray();
        for (SpaceArray spaceArray : spaceArrays) {
            JSONObject attachmentJson = new JSONObject();
            attachmentJson.put("fileId", spaceArray.getFileId());
            attachmentJson.put("fileName", spaceArray.getFileName());
            attachmentJson.put("fileType", spaceArray.getFileType());
            attachmentJson.put("spaceId", spaceArray.getSpaceId());
            attachmentJson.put("fileSize", spaceArray.getFileSize());
            array.add(attachmentJson);
        }
        form.setName(appendixForm.getName());
        form.setValue(array.toJSONString());
        return form;
    }

    private FormComponentValueVo generateFormWithDetailForm(DetailForm detailForm) {
        FormComponentValueVo form = new FormComponentValueVo();
        form.setName(detailForm.getName());

        List<FormComponentValueVo> forms = new ArrayList<FormComponentValueVo>();
        if (!ArrayUtil.isEmpty(detailForm.getTextForms())) {
            for (TextForm textForm : detailForm.getTextForms()) {
                forms.add(generateFormWithTextForm(textForm));
            }
        }

        if (!ArrayUtil.isEmpty(detailForm.pictureForms)) {
            for (PictureForm pictureForm : detailForm.pictureForms) {
                forms.add(generateFormWithPictureForm(pictureForm));
            }
        }

        form.setValue(JSON.toJSONString(Arrays.asList(forms)));

        return form;
    }
}
