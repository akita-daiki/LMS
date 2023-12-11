package com.book.service;

import java.util.ArrayList;
import java.util.List;

import com.book.constants.BookConstants.GenreType;
import com.book.util.LabelValueBean;

public class SelectListService {
	
	
	public List<LabelValueBean> createBookGenreList() {

        List<LabelValueBean> beanList = new ArrayList<LabelValueBean>();

        for (GenreType type : GenreType.values()) {
        	LabelValueBean bean = new LabelValueBean();
            bean.setValue(String.valueOf(type.getCode()));
            bean.setLabel(GenreType.getGenre(type.getCode()));
            beanList.add(bean);
        }

        return beanList;
    }

}
