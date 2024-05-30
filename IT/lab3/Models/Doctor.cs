using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace MVC_lab2.Models
{
    public class Doctor
    {
        public Doctor() {
            Patients = new List<Patient>();
        }

        [Key]
        public int Id { get; set; }

        [Required]
        public String Name { get; set; }

        public virtual ICollection<Patient> Patients { get; set; }

        public virtual Hospital Hospital { get; set; }
    }
}