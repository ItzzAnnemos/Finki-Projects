using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace MVC_lab2.Models
{
    public class DoctorHospital
    {
        public DoctorHospital() {
            Hospitals = new List<Hospital>();
        }
        public int DoctorId { get; set; }

        public int HospitalId { get; set; }

        public Doctor Doctor { get; set; }

        public List<Hospital> Hospitals { get; set; }
    }
}