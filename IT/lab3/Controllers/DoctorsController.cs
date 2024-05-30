using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using MVC_lab2.Models;

namespace MVC_lab2.Controllers
{
    public class DoctorsController : Controller
    {
        private ApplicationDbContext db = new ApplicationDbContext();

        // GET: Doctors
        public ActionResult Index()
        {
            return View(db.Doctors.ToList());
        }

        // GET: Doctors/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Doctor doctor = db.Doctors.Find(id);
            if (doctor == null)
            {
                return HttpNotFound();
            }
            return View(doctor);
        }

        // GET: Doctors/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Doctors/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "Id,Name")] Doctor doctor)
        {
            if (ModelState.IsValid)
            {
                db.Doctors.Add(doctor);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(doctor);
        }

        // GET: Doctors/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Doctor doctor = db.Doctors.Find(id);
            if (doctor == null)
            {
                return HttpNotFound();
            }
            return View(doctor);
        }

        // POST: Doctors/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "Id,Name")] Doctor doctor)
        {
            if (ModelState.IsValid)
            {
                db.Entry(doctor).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(doctor);
        }

        // GET: Doctors/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Doctor doctor = db.Doctors.Find(id);
            if (doctor == null)
            {
                return HttpNotFound();
            }
            return View(doctor);
        }

        // POST: Doctors/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            Doctor doctor = db.Doctors.Find(id);
            db.Doctors.Remove(doctor);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        public ActionResult InsertHospital(int id)
        {
            DoctorHospital model = new DoctorHospital();
            model.DoctorId = id;
            model.Doctor = db.Doctors.Find(id);
            model.Hospitals = db.Hospitals.ToList();
            return View(model);
        }

        [HttpPost]
        public ActionResult InsertDoctorHospital(DoctorHospital model)
        {
            var doctor = db.Doctors.Find(model.DoctorId);
            var hospital = db.Hospitals.Find(model.HospitalId);
            hospital.Doctors.Add(doctor);
            doctor.Hospital = hospital;
            db.SaveChanges();
            return View("Index", db.Doctors);
        }

        public ActionResult InsertPatient(int id)
        {
            DoctorPatient model = new DoctorPatient();
            model.DoctorId = id;
            model.Doctor = db.Doctors.Find(id);
            model.Patients = db.Patients.ToList();
            return View(model);
        }

        [HttpPost]
        public ActionResult InsertDoctorPatient(DoctorPatient model)
        {
            var doctor = db.Doctors.Find(model.DoctorId);
            var patient = db.Patients.Find(model.PatientId);
            if (!doctor.Patients.Contains(patient))
            {
                doctor.Patients.Add(patient);
            }
            if (!patient.Doctors.Contains(doctor))
            {
                patient.Doctors.Add(doctor);
            }
            db.SaveChanges();
            return View("Details", doctor);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
