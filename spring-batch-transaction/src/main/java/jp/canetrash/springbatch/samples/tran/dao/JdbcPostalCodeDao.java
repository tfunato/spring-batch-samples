package jp.canetrash.springbatch.samples.tran.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import jp.canetrash.springbatch.samples.tran.item.PostalCoedeItem;

public class JdbcPostalCodeDao extends JdbcDaoSupport implements PostalCodeDao {

	private String insertSql = "insert into postal_code (code, old_postal_code, postal_code, pref_kana, ward_kana, zone_kana, pref, ward, zone, flg1, flg2, flg3, flg4, flg5, flg6) "
			+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

	@Override
	public void writePostalCode(PostalCoedeItem postalCode) {

		getJdbcTemplate().update(insertSql, postalCode.getCode(), postalCode.getOldPostalCode(),
				postalCode.getPostalCode(), postalCode.getPrefKana(), postalCode.getWardKana(),
				postalCode.getZoneKana(), postalCode.getPref(), postalCode.getWard(), postalCode.getZone(),
				postalCode.getFlg1(), postalCode.getFlg2(), postalCode.getFlg3(), postalCode.getFlg4(),
				postalCode.getFlg5(), postalCode.getFlg6());
	}

}
