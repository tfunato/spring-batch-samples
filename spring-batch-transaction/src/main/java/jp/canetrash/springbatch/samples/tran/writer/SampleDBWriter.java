package jp.canetrash.springbatch.samples.tran.writer;

import java.util.List;

import jp.canetrash.springbatch.samples.tran.dao.PostalCodeDao;
import jp.canetrash.springbatch.samples.tran.item.PostalCoedeItem;

import org.springframework.batch.item.ItemWriter;

public class SampleDBWriter implements ItemWriter<PostalCoedeItem> {

	private PostalCodeDao postalCodeDao;

	public void setPostalCodeDao(PostalCodeDao postalCodeDao) {
		this.postalCodeDao = postalCodeDao;
	}

	@Override
	public void write(List<? extends PostalCoedeItem> items) throws Exception {

		for (PostalCoedeItem item : items) {
			postalCodeDao.writePostalCode(item);
		}
	}

}
