package com.xzjh.passbook.merchants;

import antlr.preprocessor.Hierarchy;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * @author DMJ
 * @date 2019-04-15 3:15
 */
public class tes1 {

//    private HashMap<String, Object> explain(ExcelUtil xlsUtil){
//
//        MultipartFile file = new MultipartFile("file");
//        if(xlsUtil == null){
//            return null;
//        }
//
//        Sheet positionSheet = null;
//        Sheet portSheet = null;
//        Sheet functionSheet = null;
//        Sheet modelSheet = null;
//        Sheet function3DSheet = null;
//
//        int sheetSize = xlsUtil.getSheetCount();
//        for(int i=0; i<sheetSize; i++){
//            xlsUtil.getSheet(i);
//            if(xlsUtil.getCurrentSheet().getSheetName().equals("positions")){
//                positionSheet = xlsUtil.getCurrentSheet();
//            }else if(xlsUtil.getCurrentSheet().getSheetName().equals("ports")){
//                portSheet = xlsUtil.getCurrentSheet();
//            }else if(xlsUtil.getCurrentSheet().getSheetName().equals("functions")){
//                functionSheet = xlsUtil.getCurrentSheet();
//            }else if(xlsUtil.getCurrentSheet().getSheetName().equals("model")){
//                modelSheet = xlsUtil.getCurrentSheet();
//            }else if(xlsUtil.getCurrentSheet().getSheetName().equals("functions_3d")){
//                function3DSheet = xlsUtil.getCurrentSheet();
//            }
//        }
//
//        xlsUtil.setCurrentSheet(modelSheet);
//        HashMap<String, Object> modelInfos = this.explainModel(xlsUtil);
//        if(modelInfos == null || modelInfos.isEmpty() || !modelInfos.containsKey("model")){
//            throw new RuntimeException("生成三维模型信息失败");
//        }
//
//        Model model = (Model)modelInfos.get("model");
//        Hierarchy hierarchy = (Hierarchy)CachePool.get("hierarchy", modelInfos.getOrDefault("层级", null));
//        if(hierarchy == null){
//            throw new RuntimeException("未找到型号对应层级信息: " + modelInfos.getOrDefault("层级", null));
//        }
//
//
//        String type = modelInfos.getOrDefault("类型", "").toString();
//        if(type.trim().isEmpty()){
//            throw new RuntimeException("未找到资产型号所属的类型: " + type);
//        }
//
//
//        AssetType assetType = (AssetType)CachePool.getOrDefault("assetTypePath", type, null);
//        if(assetType == null){
//            throw new RuntimeException("未找到资产型号所属的类型: " + type);
//        }
//
//
//        AssetModel assetModel = this.assetModelService.selectByAssetModelNameAndAssetTypeID(model.getModelName(), assetType.getAssetTypeID());
//        if(assetModel == null){
//            assetModel = new AssetModel(0, assetType.getAssetTypeID(), model.getModelName(), 1, model.getModelID(), hierarchy.getHierarchyID(), "", null);
//            this.assetModelService.insert(assetModel);
//        }else{
//            assetModel = new AssetModel(assetModel.getAssetModelID(), assetType.getAssetTypeID(), model.getModelName(), 1, model.getModelID(), hierarchy.getHierarchyID(), "", null);
//            this.assetModelService.update(assetModel);
//        }
//
//        xlsUtil.setCurrentSheet(positionSheet);
//        this.explainPosition(xlsUtil, assetModel.getAssetModelID(), model, assetModel);
//
//        xlsUtil.setCurrentSheet(portSheet);
//        this.explainPort(xlsUtil, assetModel.getAssetModelID());
//
//        xlsUtil.setCurrentSheet(functionSheet);
//        this.explainFunctions(xlsUtil, assetModel.getAssetModelID());
//
//        xlsUtil.setCurrentSheet(function3DSheet);
//        this.explainFunctions3D(xlsUtil, assetModel.getAssetModelID());
//
//        return modelInfos;
//    }
//
//
//
//
//    /**
//     *
//     * @Description: 解析资产型号关联的模型信息
//     * @User: CPC
//     * @Date: 2016年3月14日 下午2:18:28
//     * @param xlsUtil
//     */
//    public HashMap<String, Object> explainModel(ExcelUtil xlsUtil){
//        if(xlsUtil.getCurrentSheet() == null || xlsUtil.getCurrentSheet().getSheetName().indexOf("model") == -1){
//            throw new RuntimeException("explain the model information, the sheet name must contains model");
//        }
//
//        List<List<Object>> cells = xlsUtil.getSheetCells();
//        if(cells == null || cells.isEmpty()){
//            throw new RuntimeException("explain the model information, can not find the model information");
//        }
//
//        HashMap<String, Object> modelInfos = new HashMap<String, Object>();
//
//        for(List<Object> cell : cells){
//            if(cell == null || cell.size() < 2){
//                continue;
//            }
//
//            Object key = cell.get(0);
//            Object value = cell.get(1);
//
//            if(key == null || value == null){
//                continue;
//            }
//            modelInfos.put(String.valueOf(key), value);
//        }
//
//
//        String modelName = modelInfos.getOrDefault("名称", "").toString();
//        if(modelName.isEmpty()){
//            throw new RuntimeException("模型名称不能为空");
//        }
//
//        String modelType = modelInfos.getOrDefault("类型", "").toString();
//        if(modelType == null || modelType.trim().isEmpty()){
//            throw new RuntimeException("模型所属类型不能为空");
//        }
//
//        int modelVersion = NumberUtil.parseIntOrDefault(modelInfos.getOrDefault("版本", null), 1);
//
//        String modelFileDir = modelFilePath + modelType + File.separator + modelName + File.separator + modelVersion;
//        File modelTypePath = new File(RequestUtil.getRootPath() +  modelFileDir);
//        if(!modelTypePath.exists() && !modelTypePath.mkdirs()){
//            throw new RuntimeException("创建模型路径失败");
//        }
//        modelInfos.put("modelDir", modelFileDir);
//
//        float modelHeight = NumberUtil.parseFloatOrDefault(modelInfos.getOrDefault("高度", ""), 0.f);
//        float modelWidth = NumberUtil.parseFloatOrDefault(modelInfos.getOrDefault("宽度", ""), 0.f);
//        float modelDepth = NumberUtil.parseFloatOrDefault(modelInfos.getOrDefault("深度", ""), 0.f);
//
//        int modelPositionSize = NumberUtil.parseIntOrDefault(modelInfos.getOrDefault("位置大小", null), 1);
//
//        float modelWeight = NumberUtil.parseFloatOrDefault(modelInfos.getOrDefault("重量", ""), 0.f);
//        float modelPower = NumberUtil.parseFloatOrDefault(modelInfos.getOrDefault("额定功率", ""), 0.f);
//
//        float modelBearWeight = NumberUtil.parseFloatOrDefault(modelInfos.getOrDefault("最大承载重量", ""), 0.f);
//        float modelBearPower = NumberUtil.parseFloatOrDefault(modelInfos.getOrDefault("最大承载功率", ""), 0.f);
//
//        String modelBrand = modelInfos.getOrDefault("品牌", "-").toString();
//        String modelAttach = modelInfos.getOrDefault("附加信息", "-").toString();
//
//        String modelFile = modelInfos.getOrDefault("模型", "-").toString();
//        String modelSimpleFile = modelInfos.getOrDefault("简略模型", "-").toString();
//        String modelThumbFile = modelInfos.getOrDefault("缩略图", "-").toString();
//        modelFile = modelFile.equals("-") ? modelFile : modelFileDir + File.separator + modelFile;
//        modelSimpleFile = modelSimpleFile.equals("-") ? modelSimpleFile : modelFileDir + File.separator + modelSimpleFile;
//        modelThumbFile = modelThumbFile.equals("-") ? modelThumbFile : modelFileDir + File.separator + modelThumbFile;
//
//
//        Model model = this.modelService.selectByNameAndVersion(modelName, modelVersion);
//        if(model == null){
//            model = new Model(0, modelName, modelVersion, modelWidth, modelHeight,
//                    modelDepth, modelWeight, modelPower, modelBrand, modelBearWeight, modelBearPower, modelPositionSize, ModelFlag.DEFAULT.getValue(),
//                    modelFile, modelSimpleFile, modelThumbFile, modelAttach);
//            this.modelService.insert(model);
//        }else{
//            model = new Model(model.getModelID(), modelName, modelVersion, modelWidth, modelHeight,
//                    modelDepth, modelWeight, modelPower, modelBrand, modelBearWeight, modelBearPower, modelPositionSize, ModelFlag.DEFAULT.getValue(),
//                    modelFile, modelSimpleFile, modelThumbFile, modelAttach);
//            this.modelService.update(model);
//        }
//
//        modelInfos.put("model", model);
//
//        return modelInfos;
//    }

}
