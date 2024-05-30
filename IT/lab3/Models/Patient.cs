using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace MVC_lab2.Models
{
    public class Patient
    {
        public Patient() {
            Doctors = new List<Doctor>();
        }

        [Key]
        public int Id { get; set; }

        [Required]
        public String Name { get; set; }

        [Required]
        public String Sex { get; set; }

        [Range(10000, 99999, ErrorMessage = "Patient code must be a 5 digit number!")]
        [Display(Name = "Код на пациент")]
        public int PatientCode { get; set; }

        public virtual ICollection<Doctor> Doctors { get; set; }

    }
}