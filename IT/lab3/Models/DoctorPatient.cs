using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace MVC_lab2.Models
{
    public class DoctorPatient
    {
        public DoctorPatient() {
            Patients = new List<Patient>();
        }
        public int DoctorId { get; set; }

        public int PatientId { get; set; }

        public Doctor Doctor { get; set; }

        public List<Patient> Patients { get; set; }
    }
}