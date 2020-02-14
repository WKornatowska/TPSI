package car.shop.furka4U.services;

import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import car.shop.furka4U.myObj.BodyStyle;
import car.shop.furka4U.myObj.CarManufacturer;
import car.shop.furka4U.myObj.CarModel;
import car.shop.furka4U.myObj.FuelType;
import car.shop.furka4U.myObj.Offer;
import car.shop.furka4U.myObj.OfferFilter;

@Service
@Transactional
public class OffersService {
    @PersistenceContext
    private EntityManager em;

    public CarManufacturer getCarManufacturer(int id) {
        return em.find(CarManufacturer.class, id);
    }

    public CarModel getCarModel(int id) {
        return em.find(CarModel.class, id);
    }

    public List<CarManufacturer> getCarManufacturers() {
        String jpql = "select cm from CarManufacturer cm order by cm.name";
        TypedQuery<CarManufacturer> query = em.createQuery(jpql, CarManufacturer.class);
        List<CarManufacturer> result = query.getResultList();
        return result;

    }

    public List<BodyStyle> getBodyStyles() {
        String jpql = "select bs from BodyStyle bs order by bs.name";
        TypedQuery<BodyStyle> query = em.createQuery(jpql, BodyStyle.class);
        List<BodyStyle> result = query.getResultList();
        return result;

    }

    public List<FuelType> getFuelTypes() {
        String jpql = "select cm from FuelType cm order by cm.name";
        TypedQuery<FuelType> query = em.createQuery(jpql, FuelType.class);
        List<FuelType> result = query.getResultList();
        return result;

    }

    public List<CarModel> getCarModels() {
        String jpql = "select cm from CarModel cm order by cm.name";
        TypedQuery<CarModel> query = em.createQuery(jpql, CarModel.class);
        List<CarModel> result = query.getResultList();
        return result;

    }

    public List<CarModel> getCarModels(int manufacturerId) {
        String jpql = "select cm from CarModel cm where cm.manufacturer.id = :id order by cm.name";

        TypedQuery<CarModel> query = em.createQuery(jpql, CarModel.class);
        query.setParameter("id", manufacturerId);

        return query.getResultList();
    }

    public List<Offer> getOffers() {
        String jpql = "select cm from Offer cm order by cm.id";

        TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);

        return query.getResultList();
    }

    public int countOffers() {
        Query query = em.createNativeQuery("SELECT COUNT(*) FROM Offer");
        return ((BigInteger) query.getSingleResult()).intValue();
    }

    public List<Offer> getOffers(int page, int perPage) {
        String jpql = "select cm from Offer cm order by cm.id";

        TypedQuery<Offer> query = em.createQuery(jpql, Offer.class).setFirstResult(page * perPage)
                .setMaxResults(perPage);

        return query.getResultList();
    }

    public List<Offer> getOffersByModel(int modelId) {
        String jpql = "select cm from Offer cm where cm.model_id = :id order by cm.id";

        TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
        query.setParameter("id", modelId);

        return query.getResultList();
    }

    public List<Offer> getOffersByManufacturer(int modelId) {
        List<CarModel> carModels = getCarModels(modelId);
        List<Offer> offers = new ArrayList<Offer>();
        for (CarModel carModel : carModels) {
            offers.addAll(getOffersByModel(carModel.getId()));
        }
        return offers;
    }

    public Offer getOffer(int offerId) {
        return em.find(Offer.class, offerId);
    }

    public Offer createOffer(Offer offer) {
        System.out.println("DODAWANIE OFERTY");
        System.out.println(offer.toString());
        em.persist(offer);
        return offer;
    }

    public Offer deleteOffer(Integer id) {
        Offer offer = em.find(Offer.class, id);
        em.remove(offer);
        return offer;
    }

    public Offer saveOffer(Offer offer) {
        return em.merge(offer);
    }

}