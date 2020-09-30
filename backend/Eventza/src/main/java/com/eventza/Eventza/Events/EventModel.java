package com.eventza.Eventza.Events;

import com.eventza.Eventza.Categories.CategoryModel;
import com.eventza.Eventza.model.User;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class EventModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false)
  private UUID id;
  private String eventName;
  @Lob
  private byte[] imageByte;
  private String organiserName;
  private String eventLocation;
  private Integer price;
  private Double averageRating = 0.0;
  private Integer totalRating = 0;
  private Integer ratingCounter = 0;
  private Integer totalTickets;
  private Integer registrations = 0;
  private String startDate;
  private String startTime;
  private String endDate;
  private String endTime;
  private String eventDescription;
  private String brochure_name;
  @ManyToOne(cascade = CascadeType.ALL)
  private CategoryModel category;
  @OneToMany
  private List<User> registeredUsers;


  public EventModel() {

  }

  public EventModel(String eventName, String organiserName, String startDate, String startTime,
      String lastDate, String endTime,
      String eventLocation, Integer price, Integer totalTickets, String eventDescription)
      throws ParseException {
    this.id = UUID.randomUUID();
    this.eventName = eventName;
    this.imageByte = null;
    this.organiserName = organiserName;
//  this.endDate = new Date(lastDate);
//  this.startDate = new Date(startDate);
    this.endDate = parseDate(lastDate);
    this.endTime = parseTime(endTime);
    this.startDate = parseDate(startDate);
    this.startTime = parseTime(startTime);
    this.eventLocation = eventLocation;
    this.price = price;
    this.totalTickets = totalTickets;
    this.eventDescription = eventDescription;
    this.brochure_name = null;
  }

  private static String parseDate(String date) {
    try {
      Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
      return new SimpleDateFormat("yyyy-MM-dd").format(d).substring(0, 10);
    } catch (ParseException e) {
      return null;
    }
  }

  private static String parseTime(String time) throws ParseException {

    DateFormat sdf = new SimpleDateFormat("HH:mm");
    Date date = sdf.parse(time);
    String t = sdf.format(date);
    return t;
  }

  public Integer counter() {
    return ++ratingCounter;
  }

  public Integer getTotalRating() {
    return totalRating;
  }

  public void setTotalRating(Integer rating) {
    this.totalRating += rating;
  }

  public UUID getId() {
    return id;
  }


  public byte[] getImageByte() {
    return imageByte;
  }

  public void setImageByte(byte[] imageByte) {
    this.imageByte = imageByte;
  }


  public void setStartDate(String eventDate) {
    this.startDate = eventDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public Integer getRemainingTickets() {
    return totalTickets - registrations;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public String getOrganiserName() {
    return organiserName;
  }

  public void setOrganiserName(String organiserName) {
    this.organiserName = organiserName;
  }

  public String getStartDate() {
    return startDate;
  }

  public String getEventLocation() {
    return eventLocation;
  }

  public void setEventLocation(String eventLocation) {
    this.eventLocation = eventLocation;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public Double getAverageRating() {
    return averageRating;
  }

  public void setAverageRating(Double averageRating) {
    this.averageRating = averageRating;
  }

  public int getTotalTickets() {
    return totalTickets;
  }

  public void setTotalTickets(int totalTickets) {
    this.totalTickets = totalTickets;
  }

  public int getRegistrations() {
    return registrations;
  }

  public void setRegistrations(int registrations) {
    this.registrations = registrations;
  }

  public String getEventDescription() {
    return eventDescription;
  }

  public void setEventDescription(String eventDescription) {
    this.eventDescription = eventDescription;
  }

  public List<User> getRegisteredUsers() {
    return registeredUsers;
  }

  public void setRegisteredUsers(List<User> registeredUsers) {
    this.registeredUsers = registeredUsers;
  }

  public CategoryModel getCategory() {
    return category;
  }

  public void setCategory(CategoryModel category) {
    this.category = category;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public Integer getRatingCounter() {
    return ratingCounter;
  }

  public void setRatingCounter(Integer ratingCounter) {
    this.ratingCounter = ratingCounter;
  }

  public void setTotalTickets(Integer totalTickets) {
    this.totalTickets = totalTickets;
  }

  public void setRegistrations(Integer registrations) {
    this.registrations = registrations;
  }

  public String getBrochure_name() {
    return brochure_name;
  }

  public void setBrochure_name(String brochure_url) {
    this.brochure_name = brochure_url;
  }
}
