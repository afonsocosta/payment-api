package br.com.payment.api.repository;

import br.com.payment.api.model.entity.BuyerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerInfoRepository extends JpaRepository<BuyerInfo, Long> {
}
